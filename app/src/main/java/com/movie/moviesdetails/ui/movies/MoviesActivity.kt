package com.movie.moviesdetails.ui.movies

import com.movie.moviesdetails.R
import com.movie.moviesdetails.base.BaseNavigationGraphActivity
import com.movie.moviesdetails.base.BaseViewModel
import com.movie.moviesdetails.databinding.ActivityMoviesBinding

class MoviesActivity : BaseNavigationGraphActivity<BaseViewModel , ActivityMoviesBinding>() {
    override fun getNavFragmentContainerId() = R.id.movies_fragment_container
    override fun getContentView() = R.layout.activity_movies
}