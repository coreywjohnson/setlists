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
public class SearchSetlistByArtistInteractor {
    private SetlistService mSetlistService;
    private Call<Setlists> mRequest;

    @Inject
    public SearchSetlistByArtistInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    public void execute(String query, int pageNo, final SearchSetlistByArtistListener listener) {
        cancel();
        mRequest = mSetlistService.searchSetlistsByArtist(query, pageNo);
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
