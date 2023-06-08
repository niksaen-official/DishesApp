package com.niksaen.test.bag

import android.util.Log
import com.niksaen.test.remote.dishes.DishesItem

class BagModule(private var list: ArrayList<DishesItem>) {
    private val bag = HashMap<Int,Int>()
    fun add(dishesItem: DishesItem){
        if(bag.containsKey(dishesItem.id)){
            bag[dishesItem.id]=bag[dishesItem.id]!!+1
        }else{
            bag[dishesItem.id] = 1
        }
    }
    fun remove(dishesItem: DishesItem){
        if(bag.containsKey(dishesItem.id)){
            bag[dishesItem.id] = bag[dishesItem.id]!!-1
            if(bag[dishesItem.id] == 0){
                bag.remove(dishesItem.id)
            }
        }
    }
    fun getDishesCount(dishesItem: DishesItem):Int =
        if(bag.containsKey(dishesItem.id)) bag[dishesItem.id]!!
        else 0

    fun getBagList():ArrayList<DishesItem>{
        val bagList=ArrayList<DishesItem>()
        for(dishesId in bag.keys){
            for(dishes in list){
                if(dishes.id == dishesId) bagList.add(dishes)
            }
        }
        Log.w("BagModule",bagList.joinToString())
        return bagList
    }
    fun getPrice():Int{
        var price=0
        for (productId in bag.keys){
            for(product in list){
                if(product.id == productId){
                    price+=product.price*bag[productId]!!
                }
            }
        }
        return price
    }
}
