package com.example.pestpro.api
import retrofit2.Call
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.http.*


interface Api {
    @Multipart
    @POST("api/registration_api/")
    fun createUser(
        @Part("email") email: RequestBody,
        @Part("password1") password1: RequestBody,
        @Part("password2") password2: RequestBody,
        @Part("username") username: RequestBody
    ): Call<JsonObject>

    @Multipart
    @POST("api/login_api/")
    fun userLogin(
        @Part("password") password: RequestBody,
        @Part("username") username: RequestBody
    ): Call<JsonObject>


}








