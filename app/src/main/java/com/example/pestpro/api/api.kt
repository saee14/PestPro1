package com.example.pestpro.api
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File


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

    @Multipart
    @POST("api/transaction_api/")
    fun imagepost(
        @Part ("trans_media") trans_media: File,
        @Part("trans_from") trans_from: RequestBody,
        @Part("trans_msg") trans_msg: RequestBody,
        @Part("device_udid") device_udid: RequestBody,
        @Part("device_type") device_type: RequestBody

    ): Call<JsonObject>



}