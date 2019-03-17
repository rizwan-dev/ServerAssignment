package com.riztech.assignment.network;

import android.text.GetChars;

import com.riztech.assignment.model.MeetupResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("2/groups")
    public Call<MeetupResponse> getMeetupResponse(@Query("lat") double lat, @Query("lon") double lon,
                                                  @Query("page") int page, @Query("key") String key);


}
