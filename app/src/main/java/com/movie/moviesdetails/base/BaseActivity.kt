package com.movie.moviesdetails.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VM: BaseViewModel, VB: ViewDataBinding> : AppCompatActivity() {


    protected var viewModel: VM ?= null

    protected lateinit var viewBinding: VB
    protected abstract fun prepareUI()
    protected abstract fun getContentView(): Int
    protected open fun initializeViewModel(){}
    protected open fun subscribeObservers(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getContentView())
        prepareUI()
        initializeViewModel()
        subscribeObservers()
    }

    override fun attachBaseContext(newBase: Context?) {
        val newOverride = Configuration(newBase?.resources?.configuration)
        if (newOverride.fontScale > 1f){
            newOverride.fontScale = 1f
        }
        applyOverrideConfiguration(newOverride)
        super.attachBaseContext(newBase)
    }



}