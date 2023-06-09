package com.niksaen.test.ui.bag

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niksaen.test.DishesApplication
import com.niksaen.test.modules.BagModule
import com.niksaen.test.modules.UserCity
import com.niksaen.test.modules.getUserCity
import com.niksaen.test.remote.dishes.DishesItem
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BagViewModel : ViewModel() {
    val context:Context by inject(Context::class.java)
    val bagModule:BagModule by inject(BagModule::class.java)
    private var _priceText = MutableLiveData<String>()
    private var _bagList = MutableLiveData<ArrayList<DishesItem>>()
    val fullPriceText:LiveData<String> = _priceText
    val bagList:LiveData<ArrayList<DishesItem>> = _bagList

    fun requestBagList(){
        _bagList.value = bagModule.getBagList()
    }
    fun requestFullPrice(){
        if(bagModule.getPrice()>0)
            _priceText.value = "Оплатить "+bagModule.getPrice()
        else
            _priceText.value = "Оплатить"
    }
    fun setDataChangedAction(){
        bagModule.dataChangedAction= Runnable{
            requestBagList()
            requestFullPrice()
        }
    }
    fun getDate():String{
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(currentDate).replaceFirst("0","")
    }
    fun getCity():String{
        return when(val handle = getUserCity(context)){
            is UserCity.Error->"Error"
            is UserCity.Success->handle.location
        }
    }
}