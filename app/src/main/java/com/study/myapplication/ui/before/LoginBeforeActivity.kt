package com.study.myapplication.ui.before

import android.os.Bundle
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.study.myapplication.R
import com.study.myapplication.base.BaseActivity
import com.study.myapplication.bean.BannerResponseBean
import com.study.myapplication.databinding.ActivityBeforeBinding
import com.study.myapplication.extension.displayWithUrl
import com.study.myapplication.extension.viewModel


class LoginBeforeActivity: BaseActivity<ActivityBeforeBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_before

    val vm by viewModel<LoginBeforeVM>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBanner()
        binding.vm = vm
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
                    //点击页面跳转逻辑
                }
            }
        }
    }
}