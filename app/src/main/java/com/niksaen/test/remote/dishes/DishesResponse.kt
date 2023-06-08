package com.niksaen.test.remote.dishes

import com.google.gson.annotations.SerializedName

data class DishesResponse(val dishes:ArrayList<DishesItem>)
data class DishesItem(
    val id:Int,
    val name:String,
    val price:Int,
    val weight:Int,
    val description:String,
    val image_url:String,
    @SerializedName("tegs")
    val tags:Array<String>
)