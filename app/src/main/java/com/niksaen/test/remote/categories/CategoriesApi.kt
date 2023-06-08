package com.niksaen.test.remote.categories

import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {
    @GET("./v3/058729bd-1402-4578-88de-265481fd7d54")
    fun getCategories():Single<CategoriesResponse>
}