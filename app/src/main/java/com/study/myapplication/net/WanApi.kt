package com.study.myapplication.net

import androidx.lifecycle.LiveData
import com.study.myapplication.BuildConfig
import com.study.myapplication.RequestBean.EmptyRequestBean
import com.study.myapplication.RequestBean.IdBorrowProductRequestBody
import com.study.myapplication.bean.BannerResponseBean
import com.study.myapplication.bean.BorrowProductBean
import com.study.myapplication.net.GLURLConst
import com.study.myapplication.net.MyRetrofitConverterFactory
import com.study.myapplication.util.SP
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.*
import java.util.HashMap
import java.util.concurrent.TimeUnit

interface WanApi {
    companion object {
        fun get(): WanApi {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
            }
            clientBuilder.cookieJar(object : CookieJar {
                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    if (url.toString().startsWith(BuildConfig.ID_BaseUrl)) {
                        SP.saveCookies(cookies)
                    }
                }

                override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
                    return SP.getCookies()
                }
            })
            return Retrofit.Builder()
                .baseUrl(BuildConfig.ID_BaseUrl)
                .client(clientBuilder.build())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(MyRetrofitConverterFactory.create())
                .build()
                .create(WanApi::class.java)
        }
    }

    fun  idYn_getHeader(): Map<String, String> {
        val headerMap = HashMap<String, String>()
        headerMap["Content-Type"] = "application/json"
        headerMap["Accept"] = "application/json"
        headerMap["config"] = BuildConfig.ID_Config
        headerMap["version"] = "" + BuildConfig.VERSION_NAME
        headerMap["token"] = ""
        return headerMap
    }

    fun idYn_getImgHeader(): Map<String, String> {
        val headerMap = HashMap<String, String>()
        headerMap["Accept"] = "application/json"
        headerMap["config"] = BuildConfig.ID_Config
        headerMap["version"] = "" + BuildConfig.VERSION_NAME
        headerMap["token"] = ""
        headerMap["cNo"] = "VNM"
        return headerMap
    }


    /**
     * 首页banner
     */
    @POST(GLURLConst.KGetConfigBannerApi)
    fun bannerList(@Body bean: EmptyRequestBean, @HeaderMap map: Map<String, String>): LiveData<ApiResponse<List<BannerResponseBean>>>

    /**
     * 获取借款产品
     */
    @POST(GLURLConst.KListProductApi)
    fun getBorrowProduct(@Body body: IdBorrowProductRequestBody, @HeaderMap map: Map<String, String>): LiveData<ApiResponse<List<BorrowProductBean>>>

}