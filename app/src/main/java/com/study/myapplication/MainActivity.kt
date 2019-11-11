package com.study.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingMethods
import androidx.lifecycle.Observer
import cn.bingoogolapple.bgabanner.BGABanner
import com.study.myapplication.bean.BannerResponseBean
import com.study.myapplication.databinding.ActivityMainBinding
import io.github.iamyours.wandroid.base.BaseActivity
import io.github.iamyours.wandroid.extension.displayWithUrl
import io.github.iamyours.wandroid.extension.viewModel

class MainActivity:BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    val vm by viewModel<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProduct()
        initBanner()
        binding.vm =vm;
        binding.executePendingBindings()
    }

    private fun initBanner() {
        binding.banner.run {
            val adapter: BGABanner.Adapter<ImageView, BannerResponseBean> =
                BGABanner.Adapter { _, image,
                                    model,
                                    _ ->
                    image.displayWithUrl(model?.url)
                }
            setAdapter(adapter)
            setDelegate { _, _, model, _ ->
                if (model is BannerResponseBean) {
//                    WebActivity.nav(model.url, activity!!)
                }
            }
        }
    }


    private fun initProduct() {
        vm.product.observe(this, Observer {
            if (it.isNotEmpty()){
                Log.e("gggggg",it.get(0).arrovedBank)
            }
        })
    }
}
