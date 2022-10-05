package com.examples.photogalleryapp.api

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val BASE_URL = "https://pixabay.com/"
    val API_KY = "21054423-16861b903340d32f830e0cfcd"

    var TIMEOUT: Long = 60 * 1.toLong()
    val getClient: ApiInterface
        get() {
            val logging = HttpLoggingInterceptor()

            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val httpClint=OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)

            httpClint.addInterceptor(logging)
                .addInterceptor{chain ->
                    val original: Request = chain.request()
                    val originalHttpUrl: HttpUrl = original.url
                    val url :HttpUrl=originalHttpUrl.newBuilder()
                        .addQueryParameter("key", API_KY)
                        .build()
                  val requestBuilder: Request.Builder =original.newBuilder()
                      .url(url)
                    val request:Request =requestBuilder.build()
                    return@addInterceptor chain.proceed(request)
                }
            val gson =GsonBuilder().setLenient().create()
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)

        }
}