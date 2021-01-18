package com.example.data.di

import android.media.session.MediaSession
import com.example.data.apiservice.APIConstants.BaseUrl
import com.example.data.apiservice.APIConstants.Token
import com.example.data.apiservice.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*

@Module
public class NetworkModule {

    private var okHttpClient: OkHttpClient? = null
    private var httpClient:OkHttpClient.Builder? = null

    public fun initOkHttp() {
        val builder = OkHttpClient.Builder()
        builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
         httpClient= OkHttpClient().newBuilder()
               // .sslSocketFactory(sslSocketFactory!!,xtm)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request()
                                .newBuilder()
                                .header("Content-Type", "application/json")
                                .header("Accept", "application/json")
                                .header("Authorization","Bearer "+Token)

                            .build()
                        return chain.proceed(request)
                    }
                })
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient!!.addInterceptor(interceptor)
        okHttpClient = httpClient!!.build()
    }

    @Provides
    public fun providePostApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    public fun provideRetrofitInterface(): Retrofit {
        initOkHttp()
        return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(httpClient?.build())
                //.addConverterFactory(MoshiConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

    }
}
