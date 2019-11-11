package com.sxt.study.livedata.ui.home

import android.os.Bundle
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.study.myapplication.R
import com.study.myapplication.base.BaseFragment
import com.study.myapplication.bean.BannerResponseBean
import com.study.myapplication.databinding.FragmentHomeBinding
import com.study.myapplication.extension.displayWithUrl
import com.study.myapplication.extension.viewModel
import com.study.myapplication.ui.home.HomeFragmentVM


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_home

    val vm by viewModel<HomeFragmentVM>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBanner()
        binding.vm = vm
        binding.executePendingBindings()
    }

    private fun initBanner() {
        binding.fragmentHomeBanner.run {
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
}
