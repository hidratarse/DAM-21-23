package com.example.pmdm_2223.EVA2.retrofit_listview.api;

import com.example.pmdm_2223.EVA2.retrofit_listview.data.VolumesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookSearchService {
    @GET("/books/v1/volumes")
    Call<VolumesResponse> searchVolumes(
            @Query("q") String query,
            @Query("inauthor") String author
    );
    @GET("/books/v1/volumes")
    Call<VolumesResponse> extendVolumes(
            @Query("q") String query,
            @Query("inauthor") String author,
            @Query("maxResults") int size
    );
}