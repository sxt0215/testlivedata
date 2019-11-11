package com.study.myapplication.net

import androidx.annotation.Keep

@Keep
data class ApiResponse<T>(
    var data: T?,
    var code: Int,
    var msg: String
)