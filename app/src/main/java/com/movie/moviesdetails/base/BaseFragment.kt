package com.movie.moviesdetails.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM: BaseViewModel, VB: ViewDataBinding>: Fragment(){

    protected lateinit var viewBinding: VB
    protected var viewModel: VM ?= null

    open var permissionsNotGrantedAction: (() -> Unit)? = null
    open var permissionsGrantedAction: (() -> Unit)? = null

    protected abstract fun prepareUI()
    protected abstract fun getContentView(): Int
    protected open fun initializeViewModel(){}
    protected open fun handleMenu(){}
    protected open fun subscribeObservers(){}
    protected open fun initializeClickListeners(){}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater , getContentView() , container , false)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleMenu()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        prepareUI()
        initializeClickListeners()
        subscribeObservers()
    }

}