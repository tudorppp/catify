package com.example.baseproject.repository.authentication.username

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsernameLoginApi {

    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<String>

}