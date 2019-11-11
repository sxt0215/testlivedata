package com.study.myapplication.ui.before

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.study.myapplication.RequestBean.EmptyRequestBean
import com.study.myapplication.base.BaseViewModel
import com.study.myapplication.bean.BannerResponseBean
import com.study.myapplication.net.GLURLConst

class LoginBeforeVM: BaseViewModel() {

    private val bannerList = Transformations.switchMap(page) {
        val idYn_getHeader = GLURLConst.idYn_getHeader()
//        api.bannerList(EmptyRequestBean(),idYn_getHeader)
        api.bannerList(EmptyRequestBean(),idYn_getHeader)
    }

    val banners: LiveData<List<BannerResponseBean>> = Transformations.map(bannerList) {
        it.data ?: ArrayList()
    }
}