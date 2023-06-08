package com.niksaen.test.ui.bag

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niksaen.test.DishesApplication
import com.niksaen.test.MainActivity
import com.niksaen.test.remote.dishes.DishesItem

class BagViewModel : ViewModel() {
    private var _userCity=MutableLiveData<String>()
    private var _currentDate=MutableLiveData<String>()
    private var _price = MutableLiveData<Int>()
    private var _bagList = MutableLiveData<ArrayList<DishesItem>>()

    val userCity:LiveData<String> = _userCity
    val currentDate:LiveData<String> = _currentDate
    val fullPrice:LiveData<Int> = _price
    val bagList:LiveData<ArrayList<DishesItem>> = _bagList
    lateinit var activity:MainActivity

    fun requestUserCity(){
        _userCity.value = activity.getUserCity()
    }
    fun requestCurrentDate(){
        _currentDate.value = activity.getCurrentDate()
    }
    fun requestBagList(){
        _bagList.value = (activity.application as DishesApplication).bagModule.getBagList()
    }
    fun requestFullPrice(){
        _price.value = (activity.application as DishesApplication).bagModule.getPrice()
    }
    fun setDataChangedAction(){
        (activity.application as DishesApplication).bagModule.dataChangedAction= Runnable{
            requestBagList()
            requestFullPrice()
        }
    }
}