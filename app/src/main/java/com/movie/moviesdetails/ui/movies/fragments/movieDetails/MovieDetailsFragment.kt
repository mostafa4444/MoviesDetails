package com.movie.moviesdetails.ui.movies.fragments.movieDetails

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.movie.moviesdetails.R
import com.movie.moviesdetails.base.BaseFragment
import com.movie.moviesdetails.databinding.FragmentMovieDetailsBinding
import com.movie.moviesdetails.models.movies.MovieFilterResponse
import com.movie.moviesdetails.models.network.DataState
import com.movie.moviesdetails.models.network.ProgressBarState
import com.movie.moviesdetails.utils.NetworkConstants.BASE_IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel , FragmentMovieDetailsBinding>(){

    val args: MovieDetailsFragmentArgs by navArgs()
    override fun prepareUI() {
        viewModel?.getMovieDetailById(args.movieId)
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
        observeMovie()
    }

    override fun initializeViewModel() {
        val vModel: MovieDetailsViewModel by viewModels()
        viewModel = vModel
    }
    override fun getContentView() = R.layout.fragment_movie_details

    private fun observeMovie(){
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel?.movieState?.collect{dataState->
                    when(dataState){
                        is DataState.Success ->{
                            if (dataState.data != null){
                                viewBinding.apiStatus.visibility = View.GONE
                                viewBinding.infoLayer.visibility = View.VISIBLE
                                bindMovieInfo(dataState.data)
                            }else{
                                viewBinding.apiStatus.visibility = View.VISIBLE
                                viewBinding.apiStatus.text = getString(R.string.general_error)
                                viewBinding.infoLayer.visibility = View.GONE
                            }
                        }
                        is DataState.Error ->{
                            dataState.exception?.let {
                                viewBinding.apiStatus.visibility = View.VISIBLE
                                viewBinding.apiStatus.text = getString(R.string.general_error)
                                viewBinding.infoLayer.visibility = View.GONE
                            }
                        }
                        is DataState.Loading ->{
                            if (dataState.progressBarState == ProgressBarState.Idle){
                                viewBinding.infoLayer.visibility = View.GONE
                            }else{
                                viewBinding.apiStatus.visibility = View.VISIBLE
                                viewBinding.apiStatus.text = getString(R.string.loading)
                                viewBinding.infoLayer.visibility = View.GONE
                            }
                        }
                    }
                }

            }

        }
    }

    private fun bindMovieInfo(data: MovieFilterResponse){
        viewBinding.title.text = data.original_title
        viewBinding.date.text = data.extractReleaseYear()
        viewBinding.description.text = data.overview

        Glide.with(requireContext())
            .load(BASE_IMAGE_URL + data.poster_path)
            .error(R.drawable.baseline_block_24)
            .placeholder(R.drawable.baseline_block_24)
            .into(viewBinding.movieImg)
    }

}