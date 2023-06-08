package com.niksaen.test.di

import com.niksaen.test.bag.BagModule
import org.koin.dsl.module

val bagModule = module {
    factory {
        BagModule()
    }
}