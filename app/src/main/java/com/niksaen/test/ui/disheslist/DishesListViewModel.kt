package com.niksaen.test.ui.disheslist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niksaen.test.DishesApplication
import com.niksaen.test.MainActivity
import com.niksaen.test.R
import com.niksaen.test.bag.BagModule
import com.niksaen.test.remote.dishes.DishesApi
import com.niksaen.test.remote.dishes.DishesItem
import com.niksaen.test.remote.dishes.DishesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class DishesListViewModel(val context: Context) : ViewModel() {
    private val dishesApi: DishesApi by inject(DishesApi::class.java)
    private val compositeDisposable = CompositeDisposable()
    private val _dishesResponse= MutableLiveData<DishesResponse>()
    private val _tags = MutableLiveData<Array<String>>()

    val tags: LiveData<Array<String>> = _tags
    val dishesResponse:LiveData<DishesResponse> = _dishesResponse
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    fun addToBag(dishesItem: DishesItem){
        (context.applicationContext as DishesApplication).bagModule.add(dishesItem)
    }
    fun getTags(context: Context){
        _tags.value = context.resources?.getStringArray(R.array.dishes_tags)
    }
    fun requestDishesResponse(){
        compositeDisposable.add(
            dishesApi.getDishes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _dishesResponse.value=it },{}))
    }
    fun filterDishesByTag(tag:String = "Все меню"):ArrayList<DishesItem>{
        val dishesItemList = ArrayList<DishesItem>()
        for (dishes in dishesResponse.value!!.dishes){
            for(dishesTag in dishes.tags){
                if(dishesTag == tag){
                    dishesItemList.add(dishes)
                }
            }
        }
        return dishesItemList
    }
}