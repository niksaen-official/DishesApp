package com.niksaen.test

import android.app.Application
import com.niksaen.test.bag.BagModule
import com.niksaen.test.remote.categories.CategoriesApi
import com.niksaen.test.remote.dishes.DishesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DishesApplication: Application() {
    lateinit var categoriesApi:CategoriesApi
    lateinit var dishesApi: DishesApi
    var bagModule: BagModule?=null

    override fun onCreate() {
        super.onCreate()
        init()
    }
    private fun init(){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(loggingInterceptor)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        categoriesApi = retrofit.create(CategoriesApi::class.java)
        dishesApi = retrofit.create(DishesApi::class.java)
    }
}