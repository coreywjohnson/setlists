package com.coreywjohnson.setlists.data;

import com.coreywjohnson.setlists.models.SearchResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by corey on 25-Apr-16.
 */
public class RetrofitSingleton {

    private static final String API_URL = "http://api.setlist.fm/rest/0.1/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final SetlistService SETLIST_FM_SERVICE = retrofit.create(SetlistService.class);

    public static SetlistService getSingleton() {
        return SETLIST_FM_SERVICE;
    }

    public interface SetlistService {
        @GET("search/setlists.json")
        Call<SearchResponse> searchByArtist(@Query("artistName") String artist);
    }

}
