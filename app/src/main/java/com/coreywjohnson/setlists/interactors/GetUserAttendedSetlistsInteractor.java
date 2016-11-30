package com.coreywjohnson.setlists.interactors;

/**
 * Created by coreyjohnson on 30/11/2016.
 */

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.models.Setlist;
import com.coreywjohnson.setlists.models.Setlists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserAttendedSetlistsInteractor implements PaginatableRequest<Setlist> {
    public static final String URL = "user/%s/attended";

    private SetlistService mSetlistService;
    private Call<Setlists> mRequest;
    private PaginatableRequestListener<Setlist> mListener;
    private String mUsername;

    @Inject
    public GetUserAttendedSetlistsInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    @Override
    public void loadPage(int pageNo) {
        cancel();
        mRequest = mSetlistService.getSetlistsByArtist(String.format(URL, mUsername), pageNo);
        mRequest.enqueue(new Callback<Setlists>() {
            @Override
            public void onResponse(Call<Setlists> call, Response<Setlists> response) {
                if (response.isSuccessful()) {
                    mListener.onSuccess(response.body().getSetlist(), response.body().getTotal());
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
    public void setListener(PaginatableRequestListener<Setlist> listener) {
        mListener = listener;
    }

    @Override
    public void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
            mRequest = null;
        }
    }

    public void setUsername(String username) {
        mUsername = username;
    }
}

