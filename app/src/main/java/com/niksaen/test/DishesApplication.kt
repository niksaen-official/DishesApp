package com.niksaen.test

import android.app.Application
import com.niksaen.test.bag.BagModule
import com.niksaen.test.di.apiModule
import com.niksaen.test.di.appModule
import com.niksaen.test.di.retrofitModule
import com.niksaen.test.remote.categories.CategoriesApi
import com.niksaen.test.remote.dishes.DishesApi
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DishesApplication: Application() {
    val categoriesApi:CategoriesApi by inject()
    val dishesApi: DishesApi by inject()
    var bagModule: BagModule = BagModule()
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(level = Level.DEBUG)
            androidContext(this@DishesApplication)
            modules(listOf(appModule, retrofitModule, apiModule))
        }
    }
}