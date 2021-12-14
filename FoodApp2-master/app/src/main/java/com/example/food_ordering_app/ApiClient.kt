package com.example.food_ordering_app

import android.provider.SyncStateContract
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory


public class ApiClient {
    private lateinit var apiService: ApiService
    fun getApiService(): ApiService {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://android-kanini-course.cloud/")
               .addConverterFactory(GsonConverterFactory.create())

                .build()
            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }
}