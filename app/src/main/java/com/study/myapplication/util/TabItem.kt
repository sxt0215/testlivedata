package com.study.myapplication.util

import androidx.fragment.app.Fragment


class TabItem(
    var icon: Int,
    var name: String,
    var fragmentCls: Class<out Fragment>
)