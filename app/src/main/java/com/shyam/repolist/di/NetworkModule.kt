package com.shyam.repolist.di

import com.google.gson.GsonBuilder
import com.shyam.repolist.network.NetworkConstants
import com.shyam.repolist.network.RepositoryNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkService(): RepositoryNetworkService {
        return Retrofit
            .Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RepositoryNetworkService::class.java)
    }
}