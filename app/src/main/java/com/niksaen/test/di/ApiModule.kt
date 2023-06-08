package com.niksaen.test.di

import com.niksaen.test.remote.categories.CategoriesApi
import com.niksaen.test.remote.dishes.DishesApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<CategoriesApi> { get<Retrofit>().create(CategoriesApi::class.java) }
    single<DishesApi> { get<Retrofit>().create(DishesApi::class.java) }
}