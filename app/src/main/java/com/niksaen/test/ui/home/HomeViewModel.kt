package com.niksaen.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niksaen.test.remote.categories.CategoriesApi
import com.niksaen.test.remote.categories.CategoriesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel : ViewModel() {
    private val categoriesApi:CategoriesApi by inject(CategoriesApi::class.java)
    private val compositeDisposable=CompositeDisposable()
    private var _categoriesResponse=MutableLiveData<CategoriesResponse>()
    private var _currentDate=MutableLiveData<String>()

    val categoriesResponse:LiveData<CategoriesResponse> = _categoriesResponse
    val currentDate:LiveData<String> = _currentDate
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    fun requestCurrentDate(){
        _currentDate.value = getCurrentDate()
    }
    fun requestCategories(){
        compositeDisposable.add(
            categoriesApi.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _categoriesResponse.value=it },{}))
    }
    private fun getCurrentDate():String{
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(currentDate).replaceFirst("0","")
    }
}