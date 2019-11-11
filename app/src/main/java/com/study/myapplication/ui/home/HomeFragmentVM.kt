package com.study.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.study.myapplication.RequestBean.EmptyRequestBean
import com.study.myapplication.base.BaseViewModel
import com.study.myapplication.bean.BannerResponseBean
import com.study.myapplication.net.GLURLConst


class HomeFragmentVM: BaseViewModel(){
    private val bannerList = Transformations.switchMap(page) {
        api.bannerList(EmptyRequestBean(),GLURLConst.idYn_getHeader())
    }
    val banners: LiveData<List<BannerResponseBean>> = Transformations.map(bannerList) {
        it.data ?: ArrayList()
    }



}
