package com.example.jetpackcomposeinstagram.login.data.network



import com.example.jetpackcomposeinstagram.login.data.LoginClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.java

class LoginService @Inject constructor(private val loginClient: LoginClient) {
    //private val retrofit = RetrofitHelper.getRetrofit().create(LoginClient::class.java)

    suspend fun doLogin(user:String, password:String):Boolean{
        return withContext(Dispatchers.IO){
            val response = loginClient.doLogin()
            response.body()?.success ?: false

        }

    }
}