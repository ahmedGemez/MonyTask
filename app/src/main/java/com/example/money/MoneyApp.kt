package com.example.money

import android.app.Application
import com.example.money.di.components.AppComponent
import com.example.money.di.components.DaggerAppComponent

class MoneyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =  DaggerAppComponent.builder().setContext(this).build()

    }
}