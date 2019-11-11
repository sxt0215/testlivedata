package com.study.myapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.myapplication.net.WanApi

open class BaseViewModel : ViewModel() {
    val refreshTrigger = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    protected val api = WanApi.get()

    protected val page = MutableLiveData<Int>()
    val refreshing = MutableLiveData<Boolean>()
    val moreLoading = MutableLiveData<Boolean>()
    val hasMore = MutableLiveData<Boolean>()
    val autoRefresh = MutableLiveData<Boolean>()//SmartRefreshLayout自动刷新标记


    fun loadMore() {
        page.value = (page.value ?: 0) + 1
        moreLoading.value = true
    }

    /**
     * 自动刷新
     */
    fun autoRefresh() {
        autoRefresh.value = true
    }

    open fun refresh() {
        page.value = 0
        refreshing.value = true
    }

    open fun loadData() {
        refreshTrigger.value = true
        loading.value = true
    }
}