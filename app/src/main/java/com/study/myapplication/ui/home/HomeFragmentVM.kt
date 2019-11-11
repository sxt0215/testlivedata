package com.study.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.study.myapplication.RequestBean.EmptyRequestBean
import com.study.myapplication.base.BaseViewModel
import com.study.myapplication.bean.BannerResponseBean


class HomeFragmentVM: BaseViewModel(){
    private val bannerList = Transformations.switchMap(page) {
        api.bannerList(EmptyRequestBean(),api.idYn_getHeader())
    }
    val banners: LiveData<List<BannerResponseBean>> = Transformations.map(bannerList) {
        it.data ?: ArrayList()
    }



}
