package com.example.food_ordering_app

import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import java.net.CacheRequest

interface ApiService {
   @POST(Constants.Register_URL)
     fun Register(@Body request: RegisterUser)

    @POST(Constants.LOGIN_URL)
   // @FormUrlEncoded
  //  @Multipart
    fun login(@Header("Authorization") token: String,@Body request: LoginRequest):Call<LoginResponse>
    @GET(Constants.LOGIN_URL)
    fun login (@Header("Authorization") token: String): Call<LoginResponse>


}