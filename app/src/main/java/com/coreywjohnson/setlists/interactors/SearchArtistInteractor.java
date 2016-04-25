package com.coreywjohnson.setlists.interactors;

import android.util.Log;

import com.coreywjohnson.setlists.data.RetrofitSingleton;
import com.coreywjohnson.setlists.models.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corey on 25-Apr-16.
 */
public class SearchArtistInteractor implements Callback<SearchResponse> {
    private SearchArtistCallback mCallback;

    public SearchArtistInteractor(SearchArtistCallback callback) {
        mCallback = callback;
    }

    public void execute(String query) {
        Call<SearchResponse> request = RetrofitSingleton.getSingleton().searchByArtist(query);
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        if (response.isSuccessful()) {
            Log.i("response", response.toString());
            mCallback.onSuccess(response.body());
        } else {
            Log.i("Error", response.message());
            mCallback.onError(response.message());
        }
    }

    @Override
    public void onFailure(Call<SearchResponse> call, Throwable t) {
        Log.i("Search Request", "Failed");
        Log.i("Failure", t.getMessage());
    }


    public interface SearchArtistCallback {
        void onSuccess(SearchResponse searchResponse);

        void onError(String error);
    }
}
