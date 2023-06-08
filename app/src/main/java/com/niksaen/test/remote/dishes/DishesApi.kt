package com.niksaen.test.remote.dishes

import io.reactivex.Single
import retrofit2.http.GET

interface DishesApi {
    @GET("./v3/c7a508f2-a904-498a-8539-09d96785446e")
    fun getDishes():Single<DishesResponse>
}