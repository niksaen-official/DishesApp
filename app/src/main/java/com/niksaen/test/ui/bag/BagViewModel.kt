package com.niksaen.test.ui.bag

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niksaen.test.DishesApplication
import com.niksaen.test.MainActivity
import com.niksaen.test.bag.BagModule
import com.niksaen.test.remote.dishes.DishesItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BagViewModel(val context: Context) : ViewModel() {
    private var _currentDate=MutableLiveData<String>()
    private var _price = MutableLiveData<Int>()
    private var _bagList = MutableLiveData<ArrayList<DishesItem>>()

    val currentDate:LiveData<String> = _currentDate
    val fullPrice:LiveData<Int> = _price
    val bagList:LiveData<ArrayList<DishesItem>> = _bagList

    fun requestCurrentDate(){
        _currentDate.value = getCurrentDate()
    }
    fun requestBagList(){
        _bagList.value = (context.applicationContext as DishesApplication).bagModule.getBagList()
    }
    fun requestFullPrice(){
        _price.value = (context.applicationContext as DishesApplication).bagModule.getPrice()
    }
    fun setDataChangedAction(){
        (context.applicationContext as DishesApplication).bagModule.dataChangedAction= Runnable{
            requestBagList()
            requestFullPrice()
        }
    }
    private fun getCurrentDate():String{
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(currentDate).replaceFirst("0","")
    }
}