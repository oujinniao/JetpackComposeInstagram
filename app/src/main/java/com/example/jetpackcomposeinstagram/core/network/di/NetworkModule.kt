package com.example.jetpackcomposeinstagram.core.network.di

import com.example.jetpackcomposeinstagram.login.data.LoginClient
import com.example.jetpackcomposeinstagram.login.data.network.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginClient(retrofit: Retrofit): LoginClient {
        // Esta es la llamada correcta: Retrofit crea la implementación de la interfaz.
        return retrofit.create(LoginClient::class.java)
    }

    // También debes proveer la clase LoginService, ya que se usa en otras partes.
    // Ahora recibe su dependencia (LoginClient) de Hilt.
    @Singleton
    @Provides
    fun provideLoginService(loginClient: LoginClient): LoginService {
        return LoginService(loginClient)
    }
}
