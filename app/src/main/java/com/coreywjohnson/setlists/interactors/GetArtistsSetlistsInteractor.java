package com.coreywjohnson.setlists.interactors;

/**
 * Created by corey on 11-Jul-16.
 */

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.models.Setlists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetArtistsSetlistsInteractor implements PaginatableRequest<Setlists.Setlist> {
    public static final String URL = "artist/%s/setlists";

    private SetlistService mSetlistService;
    private Call<Setlists> mRequest;
    private PaginatableRequestListener<Setlists.Setlist> mListener;
    private String mArtistMbid;

    @Inject
    public GetArtistsSetlistsInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    @Override
    public void loadPage(int pageNo) {
        cancel();
        mRequest = mSetlistService.getSetlistsByArtist(String.format(URL, mArtistMbid), pageNo);
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

    @Override
    public void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
            mRequest = null;
        }
    }

    public void setArtistMbid(String artistMbid) {
        mArtistMbid = artistMbid;
    }
}

