package com.niksaen.test.di

import com.niksaen.test.modules.BagModule
import org.koin.dsl.module

val bagModule= module {
    single {
        BagModule()
    }
}