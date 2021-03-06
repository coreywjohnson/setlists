package com.coreywjohnson.setlists.data;

import com.coreywjohnson.setlists.models.Artists;
import com.coreywjohnson.setlists.models.Setlists;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by coreyjohnson on 4/05/16.
 */
public interface SetlistService {
    String BASE_API_URL = "http://api.setlist.fm/rest/0.1/";
    String NOT_FOUND_MESSAGE = "Not Found";

    @GET("search/setlists")
    Call<Setlists> searchSetlists(@Query("artistName") String artist,
                                  @Query("cityName") String city,
                                  @Query("venueName") String venue,
                                  @Query("date") String date,
                                  @Query("year") String year,
                                  @Query("tour") String tour,
                                  @Query("p") int pageNo);

    @GET("search/artists")
    Call<Artists> searchArtist(@Query("artistName") String artist, @Query("p") int pageNo);

    @GET
    Call<Setlists> getSetlistsByArtist(@Url String url, @Query("p") int pageNo);
}