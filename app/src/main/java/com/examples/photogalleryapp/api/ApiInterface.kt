package com.examples.photogalleryapp.api

import retrofit2.Call
import com.examples.photogalleryapp.models.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/")
    fun getImages(
        @Query("page") page: Int,
        @Query("q") query: String,
        @Query("q") category: String,
        @Query("editors_choice") editorChoice: Boolean
    ): Call<ImageResponse>

    @GET("api/")
    fun getImageById(@Query("id") id:Long):Call<ImageResponse>

    @GET("api/")
    fun getSimilarImages (@Query("q") keyword:String , @Query("page") page: Int)
}