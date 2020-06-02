package com.example.boredapp.utils;

import com.example.boredapp.model.ActivityModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BoredApi {
    @GET("activity/")
    Single<ActivityModel> getActivity();

    @GET("activity")
    Single<ActivityModel> getActivity(@Query("type") String type);

    @GET("activity")
    Single<ActivityModel> getActivity(@Query("participants") int participants);

    @GET("activity")
    Single<ActivityModel> getActivity(@Query("price") double price);

    @GET("activity")
    Single<ActivityModel> getActivity(@Query("type") String type, @Query("participants") int participants);

    @GET("activity")
    Single<ActivityModel> getActivity(@Query("type") String type, @Query("price") double price);

    @GET("activity")
    Single<ActivityModel> getActivity(@Query("participants") int participants, @Query("price") double price);

    @GET("activity")
    Single<ActivityModel> getActivity(@Query("type") String type, @Query("participants") int participants, @Query("price") double price);
}
