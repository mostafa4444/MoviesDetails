package com.movie.moviesdetails.base


interface BaseAdapterItemClickListener<T> {

    fun onItemClicked(item: T , position: Int)

}