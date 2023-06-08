package com.niksaen.test.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .client(get(OkHttpClient::class))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        loggingInterceptor
    }
    single {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.interceptors().add(get(HttpLoggingInterceptor::class))
        clientBuilder.build()
    }
}