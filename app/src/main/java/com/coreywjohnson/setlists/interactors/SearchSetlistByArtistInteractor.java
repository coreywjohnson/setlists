package com.coreywjohnson.setlists.interactors;

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.models.Setlists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corey on 25-Apr-16.
 */
public class SearchSetlistByArtistInteractor implements PaginatableRequest<Setlists.Setlist> {
    private SetlistService mSetlistService;
    private Call<Setlists> mRequest;
    private String mQuery;
    private PaginatableRequestListener<Setlists.Setlist> mListener;

    @Inject
    public SearchSetlistByArtistInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    @Override
    public void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
            mRequest = null;
        }
    }

    @Override
    public void loadPage(int pageNo) {
        cancel();
        mRequest = mSetlistService.searchSetlistsByArtist(mQuery, pageNo);
        mRequest.enqueue(new Callback<Setlists>() {
            @Override
            public void onResponse(Call<Setlists> call, Response<Setlists> response) {
                if (response.isSuccessful()) {
                    mListener.onSuccess(response.body().getSetlist(), Integer.parseInt(response.body().getTotal()));
                } else {
                    Log.i("Error", response.message());
                    mListener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Setlists> call, Throwable t) {
                if (!call.isCanceled()) {
                    Log.i("Search Request", "Failed");
                    Log.e("Failure", t.getMessage());
                    mListener.onError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void setListener(PaginatableRequestListener<Setlists.Setlist> listener) {
        mListener = listener;
    }
}
