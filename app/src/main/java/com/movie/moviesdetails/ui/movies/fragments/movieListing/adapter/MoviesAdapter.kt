package com.movie.moviesdetails.ui.movies.fragments.movieListing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.moviesdetails.base.BaseAdapterItemClickListener
import com.movie.moviesdetails.databinding.MovieItemBinding
import com.movie.moviesdetails.models.movies.MovieItem

class MoviesAdapter(): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var trxList: List<MovieItem> = ArrayList()
    var itemClickListener: BaseAdapterItemClickListener<MovieItem> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = MovieItemBinding.inflate(layoutInflater , parent , false)
        return ViewHolder(itemBinding)
    }

    fun submitMyList(
        myList: List<MovieItem> , clickListener: BaseAdapterItemClickListener<MovieItem>
    ){
        this.trxList = myList
        this.itemClickListener = clickListener
        notifyItemRangeInserted( 0  , this.trxList.lastIndex)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.myMovieItem = trxList[position]
        holder.bind(holder.myMovieItem)
    }

    override fun getItemCount(): Int {
        return this.trxList.size
    }

    inner class ViewHolder(val itemBinding: MovieItemBinding): RecyclerView.ViewHolder(itemBinding.root) , View.OnClickListener{
        lateinit var myMovieItem: MovieItem
        init {
            itemBinding.movieContainer.setOnClickListener(this)
        }
        fun bind(myItem: MovieItem){
            itemBinding.myItem = myItem
        }

        override fun onClick(p0: View?) {
            when(p0){
                itemBinding.movieContainer-> {
                    itemClickListener?.onItemClicked(myMovieItem , adapterPosition)
                }
            }
        }
    }
}