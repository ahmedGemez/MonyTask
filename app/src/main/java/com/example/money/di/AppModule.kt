package com.example.money.di

import android.app.Application
import android.content.Context
import com.example.money.core.ErrorHandling
import com.example.money.rx.SchedulersFacade
import com.example.money.rx.SchedulersProvider

import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @Singleton
    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider


}