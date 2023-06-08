package com.niksaen.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niksaen.test.DishesApplication
import com.niksaen.test.MainActivity
import com.niksaen.test.remote.categories.CategoriesApi
import com.niksaen.test.remote.categories.CategoriesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val compositeDisposable=CompositeDisposable()
    private var _categoriesResponse=MutableLiveData<CategoriesResponse>()
    private var _userCity=MutableLiveData<String>()
    private var _currentDate=MutableLiveData<String>()

    val categoriesResponse:LiveData<CategoriesResponse> = _categoriesResponse
    val userCity:LiveData<String> = _userCity
    val currentDate:LiveData<String> = _currentDate
    var activity: MainActivity?=null
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        activity = null
    }
    fun requestUserCity(){
        _userCity.value = activity?.getUserCity()
    }
    fun requestCurrentDate(){
        _currentDate.value = activity?.getCurrentDate()
    }
    fun requestCategories(){
        compositeDisposable.add(
            (activity?.application as DishesApplication).
            categoriesApi.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _categoriesResponse.value=it },{}))
    }
}