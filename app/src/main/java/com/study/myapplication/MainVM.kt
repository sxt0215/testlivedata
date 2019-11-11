package com.study.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.study.myapplication.RequestBean.EmptyRequestBean
import com.study.myapplication.RequestBean.IdBorrowProductRequestBody
import com.study.myapplication.bean.BannerResponseBean
import com.study.myapplication.util.AbsentLiveData
import io.github.iamyours.wandroid.base.BaseViewModel
import io.github.iamyours.wandroid.net.WanApi

class MainVM : BaseViewModel(){
    private val bannerList = Transformations.switchMap(page) {
        api.bannerList(EmptyRequestBean(),api.idYn_getHeader())
    }
    val banners: LiveData<List<BannerResponseBean>> = Transformations.map(bannerList) {
        it.data ?: ArrayList()
    }

    private val productList = Transformations.switchMap(page) {
        api.getBorrowProduct(IdBorrowProductRequestBody("1"),api.idYn_getHeader())
    }
    val product = Transformations.map(productList){
        val list = it.data ?: ArrayList()
        if (list.isNotEmpty()) {

        }
        list
    }


}
