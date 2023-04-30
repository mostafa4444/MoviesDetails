package com.movie.moviesdetails.ui.movies.fragments.movieListing

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movie.moviesdetails.R
import com.movie.moviesdetails.base.BaseAdapterItemClickListener
import com.movie.moviesdetails.base.BaseFragment
import com.movie.moviesdetails.databinding.FragmentMoviesBinding
import com.movie.moviesdetails.models.movies.MovieItem
import com.movie.moviesdetails.models.network.DataState
import com.movie.moviesdetails.models.network.ProgressBarState
import com.movie.moviesdetails.ui.movies.fragments.movieListing.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesViewModel , FragmentMoviesBinding>(){
    override fun prepareUI() {
        viewBinding.swipeToRefresh.setOnRefreshListener {
            viewModel?.getMovies()
        }
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
        observeMovies()
    }
    override fun initializeViewModel() {
        val vModel: MoviesViewModel by viewModels()
        viewModel = vModel
    }

    override fun getContentView() = R.layout.fragment_movies

    private val TAG = "MoviesFragment"
    private fun observeMovies(){
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel?.moviesState?.collect{dataState->
                    when(dataState){
                        is DataState.Success ->{
                            if (dataState.data == null || dataState.data.results.isNullOrEmpty()){
                                viewBinding.moviesRv.showMoviesDataRecycler(
                                    true
                                )
                            }else{
                                viewBinding.moviesRv.showMoviesDataRecycler(false , dataState.data.results)
                            }
                        }
                        is DataState.Error ->{
                            dataState.exception?.let {
                                viewBinding.apiStatus.showError(true)
                            }
                        }
                        is DataState.Loading ->{
                            if (dataState.progressBarState == ProgressBarState.Idle){
                                viewBinding.apiStatus.showLoading(true)
                            }else{
                                viewBinding.apiStatus.showLoading(false)
                            }
                        }
                    }
                }

            }

        }
    }

    fun RecyclerView.showMoviesDataRecycler(isEmpty: Boolean = false, results: List<MovieItem> = ArrayList()){
        if (isEmpty){
            viewBinding.swipeToRefresh.isRefreshing = false
            viewBinding.moviesRv.visibility = View.GONE
            viewBinding.apiStatus.showError(true , "No data returned from Movie Server")
        }else{
            viewBinding.swipeToRefresh.isRefreshing = false
            viewBinding.apiStatus.showError(false)
            viewBinding.moviesRv.visibility = View.VISIBLE
            MoviesAdapter().let {
                this.layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)
                it.submitMyList(
                    results ,
                    object : BaseAdapterItemClickListener<MovieItem>{
                        override fun onItemClicked(item: MovieItem, position: Int) {
                            Toast.makeText(context , "${item.original_title} Clicked" , Toast.LENGTH_SHORT).show()
                        }

                    }
                )
                this.adapter = it
            }

        }
    }

    fun TextView.showLoading(show: Boolean){
        if(show){
            viewBinding.swipeToRefresh.isRefreshing = true
            this.text = "Loading..."
            this.visibility = View.VISIBLE
        }else {
            viewBinding.swipeToRefresh.isRefreshing = false
            this.visibility = View.GONE
            viewBinding.moviesRv.visibility = View.GONE
        }
    }

    fun TextView.showError(show: Boolean , msg: String = "Something went wrong, Plz try again later"){
        if(show){
            viewBinding.swipeToRefresh.isRefreshing = false
            this.text = msg
            this.visibility = View.VISIBLE
        }else {
            viewBinding.swipeToRefresh.isRefreshing = false
            this.visibility = View.GONE
            viewBinding.moviesRv.visibility = View.GONE
        }
    }

}