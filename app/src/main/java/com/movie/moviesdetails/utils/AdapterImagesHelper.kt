package com.movie.moviesdetails.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.movie.moviesdetails.R
import com.movie.moviesdetails.utils.NetworkConstants.BASE_IMAGE_URL

@BindingAdapter("setMovieImage")
fun bindMovieImage(imageView: ImageView , imagePath: String){
    Glide.with(imageView.context)
        .load(BASE_IMAGE_URL+imagePath)
        .placeholder(R.drawable.baseline_block_24)
        .error(R.drawable.baseline_block_24)
        .centerCrop()
        .into(imageView)
}