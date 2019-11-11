package com.study.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.study.myapplication.R.*
import com.study.myapplication.databinding.ActivityMainBinding
import com.study.myapplication.ui.me.MineFragment
import com.study.myapplication.util.TabItem
import com.sxt.study.livedata.ui.home.HomeFragment
import kotlinx.android.synthetic.main.view_tab.view.*
import com.google.android.material.tabs.TabLayout
import com.study.myapplication.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = layout.activity_main
    private val tabs = arrayOf(
        TabItem(drawable.tab_home_selector, "首页", HomeFragment::class.java),
        TabItem(drawable.tab_mine_selector, "我的", MineFragment::class.java)
    )
    private val fragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
        initTabLayout()
    }

    /**
     * fragment重叠
     */
    override fun onSaveInstanceState(outState: Bundle) {
        fragments.forEach {
            supportFragmentManager.putFragment(
                outState,
                it.javaClass.simpleName,
                it
            )
        }
        super.onSaveInstanceState(outState)
    }

    private fun initFragments() {
        if (fragments.isEmpty()) {
            tabs.forEach {
                val f = it.fragmentCls.newInstance()
                fragments.add(f)
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        fragments.forEach {
            if (!it.isAdded) transaction.add(
                id.fl_content, it, it.javaClass
                    .simpleName
            ).hide(it)
        }
        transaction.commit()
    }

    private fun initTabLayout() {
        binding.tabLayout.run {
            tabs.forEach {
                addTab(newTab().setCustomView(getTabView(it)))
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {}

                override fun onTabUnselected(p0: TabLayout.Tab?) {}

                override fun onTabSelected(p0: TabLayout.Tab) {
                    initTab(p0.position)
                }
            })
            getTabAt(0)?.select()
        }
        initTab(0)
    }

    private fun getTabView(it: TabItem): View {
        val v = LayoutInflater.from(this).inflate(layout.view_tab, null)
        v.tab_icon.setImageResource(it.icon)
        v.tab_name.text = it.name
        return v
    }

    private fun initTab(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        fragments.forEachIndexed { index, fragment ->
            if (index == position) {
                transaction.show(fragment)
            } else {
                transaction.hide(fragment)
            }
        }
        transaction.commit()
    }

}
