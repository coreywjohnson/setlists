package com.coreywjohnson.setlists.interactors;

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.models.SearchResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corey on 25-Apr-16.
 */
public class SearchArtistInteractor {
    private SetlistService mSetlistService;

    @Inject
    public SearchArtistInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    public void execute(String query, final SearchArtistCallback callback) {
        Call<SearchResponse> request = mSetlistService.searchByArtist(query, 2);
        request.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("response", response.toString());
                    callback.onSuccess(response.body());
                } else {
                    Log.i("Error", response.message());
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.i("Search Request", "Failed");
                Log.i("Failure", t.getMessage());
            }
        });
    }

    public interface SearchArtistCallback {
        void onSuccess(SearchResponse searchResponse);

        void onError(String error);
    }
}
