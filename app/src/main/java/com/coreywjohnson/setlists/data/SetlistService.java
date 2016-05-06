package com.coreywjohnson.setlists.data;

import com.coreywjohnson.setlists.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by coreyjohnson on 4/05/16.
 */
public interface SetlistService {
    String BASE_API_URL = "http://api.setlist.fm/rest/0.1/";

    @GET("search/setlists.json")
    Call<SearchResponse> searchByArtist(@Query("artistName") String artist, @Query("p") int pageNo);
}