package com.coreywjohnson.setlists.interactors;

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.models.Artist;
import com.coreywjohnson.setlists.models.Artists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistInteractor implements PaginatableRequest<Artist> {
    private SetlistService mSetlistService;
    private Call<Artists> mRequest;
    private String mQuery;
    private PaginatableRequestListener<Artist> mListener;

    @Inject
    public SearchArtistInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    @Override
    public void loadPage(int pageNo) {
        cancel();
        mRequest = mSetlistService.searchArtist(mQuery, pageNo);
        mRequest.enqueue(new Callback<Artists>() {
            @Override
            public void onResponse(Call<Artists> call, Response<Artists> response) {
                if (response.isSuccessful()) {
                    mListener.onSuccess(response.body().getArtist(), response.body().getTotal());
                } else {
                    Log.i("Error", response.message());
                    mListener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Artists> call, Throwable t) {
                if (!call.isCanceled()) {
                    Log.i("Search Request", "Failed");
                    Log.e("Failure", t.getMessage());
                    mListener.onError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void setListener(PaginatableRequestListener<Artist> listener) {
        mListener = listener;
    }

    @Override
    public void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
        }
    }

    public void setQuery(String query) {
        mQuery = query;
    }
}
