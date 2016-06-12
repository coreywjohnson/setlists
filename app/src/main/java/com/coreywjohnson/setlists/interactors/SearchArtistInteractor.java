package com.coreywjohnson.setlists.interactors;

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
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Artists> call, Throwable t) {
                listener.onError(t.getMessage());
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
