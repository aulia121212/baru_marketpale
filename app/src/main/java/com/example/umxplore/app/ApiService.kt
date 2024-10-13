package com.example.umxplore.app

import com.example.umxplore.model.ResponModel
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.FormUrlEncoded

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") nomortlp: String,
        @Field("password") password: String
    ): Call<ResponModel>

    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponModel>





}
