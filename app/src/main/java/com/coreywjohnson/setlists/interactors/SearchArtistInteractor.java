package com.coreywjohnson.setlists.interactors;

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.models.Setlists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corey on 25-Apr-16.
 */
public class SearchArtistInteractor {
    private SetlistService mSetlistService;
    private Call<Setlists> request;

    @Inject
    public SearchArtistInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    public void execute(String query, int pageNo, final SearchArtistCallback callback) {
        cancel();
        request = mSetlistService.searchByArtist(query, pageNo);
        request.enqueue(new Callback<Setlists>() {
            @Override
            public void onResponse(Call<Setlists> call, Response<Setlists> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    Log.i("Error", response.message());
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Setlists> call, Throwable t) {
                if (!call.isCanceled()) {
                    Log.i("Search Request", "Failed");
                    Log.e("Failure", t.getMessage());
                    callback.onError(t.getMessage());
                }
            }
        });
    }

    private void cancel() {
        if (request != null) {
            request.cancel();
            request = null;
        }
    }

    public interface SearchArtistCallback {
        void onSuccess(Setlists searchResponse);

        void onError(String error);
    }
}
