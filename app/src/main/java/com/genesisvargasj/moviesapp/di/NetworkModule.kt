package com.genesisvargasj.moviesapp.di

import com.genesisvargasj.moviesapp.data.network.ApiService
import com.genesisvargasj.moviesapp.utils.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        val gson = GsonBuilder().serializeNulls().create()
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(20, TimeUnit.SECONDS)
        httpClient.readTimeout(20, TimeUnit.SECONDS)
        val client = httpClient.build()
        return Retrofit.Builder()
            .baseUrl(AppConstants.apiURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}