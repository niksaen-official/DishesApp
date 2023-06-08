package com.niksaen.test.remote.categories

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("сategories")
    val list:ArrayList<CategoriesItem>)

data class CategoriesItem(
    val id:Int,
    val name:String,
    val image_url:String)
