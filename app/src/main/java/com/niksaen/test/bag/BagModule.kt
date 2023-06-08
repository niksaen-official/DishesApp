package com.niksaen.test.bag

import android.util.Log
import com.niksaen.test.remote.dishes.DishesItem

class BagModule {
    private val bag = ArrayList<DishesItem>()
    var dataChangedAction:Runnable = Runnable {  }
    fun add(dishesItem: DishesItem){
        bag.add(dishesItem)
        dataChangedAction.run()
    }
    fun remove(dishesItem: DishesItem){
        bag.remove(dishesItem)
        dataChangedAction.run()
    }
    fun getDishesCount(dishesItem: DishesItem):Int {
        var count = 0
        if(bag.size > 0) {
            for (dishes in bag) {
                if (dishesItem.id == dishes.id) {
                    count++
                }
            }
        }
        return count
    }


    fun getBagList():ArrayList<DishesItem>{
        val arrayList = ArrayList<DishesItem>()
        for(item in bag){
            if(!arrayList.contains(item)){
                arrayList.add(item)
            }
        }
        return arrayList
    }
    fun getPrice():Int{
        var price = 0
        if(bag.size > 0) {
            for (dishes in bag) {
                price += dishes.price
            }
        }
        return price
    }
}
