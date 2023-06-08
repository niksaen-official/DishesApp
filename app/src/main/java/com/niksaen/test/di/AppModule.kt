package com.niksaen.test.di

import com.niksaen.test.bag.BagModule
import com.niksaen.test.ui.bag.BagViewModel
import com.niksaen.test.ui.disheslist.DishesListViewModel
import com.niksaen.test.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel() }
    viewModel { BagViewModel(get()) }
    viewModel { DishesListViewModel(get()) }
}