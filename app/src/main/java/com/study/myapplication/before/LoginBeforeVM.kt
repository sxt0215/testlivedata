package com.study.myapplication.before

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.study.myapplication.RequestBean.EmptyRequestBean
import com.study.myapplication.base.BaseViewModel
import com.study.myapplication.bean.BannerResponseBean

class LoginBeforeVM: BaseViewModel() {

    private val bannerList = Transformations.switchMap(page) {
        api.bannerList(EmptyRequestBean(),api.idYn_getHeader())
    }
    val banners: LiveData<List<BannerResponseBean>> = Transformations.map(bannerList) {
        it.data ?: ArrayList()
    }
}