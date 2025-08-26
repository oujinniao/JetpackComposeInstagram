package com.example.jetpackcomposeinstagram.login.data

import com.example.jetpackcomposeinstagram.login.data.network.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/f78c9d33-28b1-4f81-9cf1-6d6ff78fa014")
    suspend fun doLogin(): Response<LoginResponse>
}