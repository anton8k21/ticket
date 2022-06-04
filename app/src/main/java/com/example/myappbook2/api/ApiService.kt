package com.example.myappbook2

import com.example.myappbook2.model.DataClass
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://travel.wildberries.ru/statistics/v1/"

private val logging = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private val okhttp = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhttp)
    .build()

interface TicketsApiService {
    @GET("cheap")
    suspend fun getAll(): Response<DataClass>

}

object TicketApi {
    val service: TicketsApiService by lazy {
        retrofit.create(TicketsApiService::class.java)
    }
}