package com.study.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import okhttp3.Cookie
import android.webkit.CookieSyncManager
import android.os.Build
import android.webkit.CookieManager
import com.study.myapplication.App
import java.lang.StringBuilder


object SP {
    val KEY_IS_LOGIN = "isLogin"
    val KEY_NICK_NAME = "nickName"
    val KEY_USER_NAME = "username"
    val KEY_DOMAIN = "domain"
    fun getSpref(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(App.instance)
    }

    fun getCookieSpref(): SharedPreferences {
        return App.instance.getSharedPreferences(
            "cookies",
            Context.MODE_PRIVATE
        )
    }

    fun put(key: String, value: String) {
        getSpref().edit().putString(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        getSpref().edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return getSpref().getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return getSpref().getString(key, "")
    }

    fun saveCookies(cookies: List<Cookie>) {
        val editor = getCookieSpref().edit()
        val domain = "www.wanandroid.com"
        val cookie = StringBuilder()
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        editor.apply()
        SP.put(KEY_DOMAIN, domain)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(App.instance)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.flush()
        }
    }

    fun getCookies(): ArrayList<Cookie> {
        val sp = getCookieSpref()
        val domain = getString(KEY_DOMAIN)
        return ArrayList<Cookie>().apply {
            val names = sp.all.keys
        }
    }

    fun logout() {
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeSessionCookies(null)
            cookieManager.removeAllCookie()
            cookieManager.flush()
        } else {
            cookieManager.removeSessionCookie()
            cookieManager.removeAllCookie()
            CookieSyncManager.getInstance().sync()
        }
        SP.put(KEY_IS_LOGIN, false)
        SP.put(KEY_NICK_NAME, "")
        SP.put(KEY_USER_NAME, "")
    }
}