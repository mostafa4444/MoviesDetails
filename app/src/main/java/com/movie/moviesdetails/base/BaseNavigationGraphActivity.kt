package com.movie.moviesdetails.base

import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseNavigationGraphActivity<VM: BaseViewModel, VB: ViewDataBinding>
    : BaseActivity<VM, VB>() {


    protected lateinit var navController: NavController
    protected lateinit var navHost: NavHostFragment

    override fun prepareUI() {
        initNavHost()
    }

    abstract fun getNavFragmentContainerId(): Int
    private fun initNavHost(){
        navHost = supportFragmentManager.findFragmentById(getNavFragmentContainerId()) as NavHostFragment
        navController = navHost.navController
    }

}