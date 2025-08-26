package com.example.jetpackcomposeinstagram.login.data.network

import com.example.jetpackcomposeinstagram.core.network.RetrofitHelper
import com.example.jetpackcomposeinstagram.login.data.LoginClient

class LoginService {
    private val retrofit = RetrofitHelper.getRetrofit().create(LoginClient::class.java)
    suspend fun doLogin(user:String, password:String):Boolean{
        return try {
            val response = retrofit.doLogin()
            response.body()?.success ?: false
        }catch (e:Exception){
            false
        }

    }
}