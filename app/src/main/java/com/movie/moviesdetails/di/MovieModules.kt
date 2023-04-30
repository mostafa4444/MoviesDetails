package com.movie.moviesdetails.di

import com.movie.moviesdetails.network.movies.MovieRepository
import com.movie.moviesdetails.network.movies.MovieRepositoryImpl
import com.movie.moviesdetails.network.movies.MovieServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object MovieModules {

    @ViewModelScoped
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MovieServices =
        retrofit.create(MovieServices::class.java)

    @ViewModelScoped
    @Provides
    fun provideMoviesRepository(service: MovieServices): MovieRepository =
        MovieRepositoryImpl(apiServices = service)

}