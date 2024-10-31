package com.project.news.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseAct<VB : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {
    protected lateinit var dataBinding: VB

    abstract fun initView()
    abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        initView()
        initObserver()
    }
}