package com.study.myapplication.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import com.study.myapplication.observer.LoadingObserver

open abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    abstract val layoutId: Int
    lateinit var binding: T
    val loadingState = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        loadingState.observe(this, LoadingObserver(this))
    }
}