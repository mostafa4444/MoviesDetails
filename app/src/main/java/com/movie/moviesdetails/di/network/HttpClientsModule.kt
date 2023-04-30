package com.movie.moviesdetails.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object HttpClientsModule {

    // incase we need to use more than value we can use the @Named


    @ViewModelScoped
    @Provides
    fun provideDefaultHttpClient(logger: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder().apply {
            addInterceptor(logger)
            callTimeout(30L , TimeUnit.SECONDS)
            connectTimeout(30L , TimeUnit.SECONDS)
            writeTimeout(30L , TimeUnit.SECONDS)
            readTimeout(30L , TimeUnit.SECONDS)
        }.build()
    }

}