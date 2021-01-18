package com.example.money.di.components

import android.app.Application
import com.example.data.di.ApiModule
import com.example.money.MainActivity
import com.example.money.di.AppModule
import com.example.money.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class]
)
interface AppComponent  {

    fun getFragmentComponentBuilder(): FragmentComponents.Builder
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(app:Application ): Builder
        fun build(): AppComponent
    }

}
