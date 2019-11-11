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
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.*
import java.net.Proxy
import java.util.HashMap
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

interface WanApi {
    companion object {
        fun get(): WanApi {
//            val ssl = SSLContext.getInstance("SSL")
//            ssl.init(null, arrayOf(TrustAllCerts()), java.security.SecureRandom())
            val clientBuilder = OkHttpClient.Builder()
//                .sslSocketFactory(ssl.socketFactory,TrustAllCerts())
                .connectTimeout(60, TimeUnit.SECONDS)
//                .proxy(Proxy.NO_PROXY)
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
//            clientBuilder.addInterceptor(object : Interceptor{
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val o = chain.request()
//                    o.newBuilder()
//                        .header("","")
//
//                }
//            })
            return Retrofit.Builder()
                .baseUrl(BuildConfig.ID_BaseUrl)
                .client(clientBuilder.build())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(MyRetrofitConverterFactory.create())
                .build()
                .create(WanApi::class.java)
        }
    }




    /**
     * 首页banner
     */
    @POST(GLURLConst.KGetConfigBannerApi)
    fun bannerList(@Body bean: EmptyRequestBean, @HeaderMap map: Map<String, String>) : LiveData<ApiResponse<List<BannerResponseBean>>>

    @POST(GLURLConst.KGetConfigBannerApi)
    fun bannerList(@Body bean: EmptyRequestBean) : LiveData<ApiResponse<List<BannerResponseBean>>>

    /**
     * 获取借款产品
     */
    @POST(GLURLConst.KListProductApi)
    fun getBorrowProduct(@Body body: IdBorrowProductRequestBody, @HeaderMap map: Map<String, String>): LiveData<ApiResponse<List<BorrowProductBean>>>

}