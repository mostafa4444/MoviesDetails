package com.movie.moviesdetails.di.network

import com.movie.moviesdetails.utils.NetworkConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlsModule {

    // incase we need to use more than value we can use the @Named

    @Singleton
    @Provides
    fun provideMovieBaseUrl() = BASE_URL


}