package com.coreywjohnson.setlists.interactors;

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.models.Artists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corey on 12-Jun-16.
 */
public class SearchArtistInteractor {
    private SetlistService mSetlistService;
    private Call<Artists> mRequest;

    @Inject
    public SearchArtistInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    public void execute(String query, int pageNo, final SearchArtistListener listener) {
        cancel();
        mRequest = mSetlistService.searchArtist(query, pageNo);
        mRequest.enqueue(new Callback<Artists>() {
            @Override
            public void onResponse(Call<Artists> call, Response<Artists> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    Log.i("Error", response.message());
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Artists> call, Throwable t) {
                if (!call.isCanceled()) {
                    Log.i("Search Request", "Failed");
                    Log.e("Failure", t.getMessage());
                    listener.onError(t.getMessage());
                }
            }
        });
    }

    public void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
        }
    }

    public interface SearchArtistListener {
        void onSuccess(Artists artists);

        void onError(String error);
    }
}
