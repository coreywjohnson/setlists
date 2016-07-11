package com.coreywjohnson.setlists.interactors;

/**
 * Created by corey on 11-Jul-16.
 */

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.models.Setlists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetArtistsSetlistsInteractor {
    public static final String URL = "artist/%s/setlists";

    private SetlistService mSetlistService;
    private Call<Setlists> mRequest;

    @Inject
    public GetArtistsSetlistsInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    public void execute(String mbid, int pageNo, final SearchSetlistByArtistListener listener) {
        cancel();
        Log.i("url", String.format(URL, mbid));
        mRequest = mSetlistService.getSetlistsByArtist(String.format(URL, mbid), pageNo);
        mRequest.enqueue(new Callback<Setlists>() {
            @Override
            public void onResponse(Call<Setlists> call, Response<Setlists> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    Log.i("Error", response.message());
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Setlists> call, Throwable t) {
                if (!call.isCanceled()) {
                    Log.i("Search Request", "Failed");
                    Log.e("Failure", t.getMessage());
                    listener.onError(t.getMessage());
                }
            }
        });
    }

    private void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
            mRequest = null;
        }
    }

    public interface SearchSetlistByArtistListener {
        void onSuccess(Setlists searchResponse);

        void onError(String error);
    }
}

