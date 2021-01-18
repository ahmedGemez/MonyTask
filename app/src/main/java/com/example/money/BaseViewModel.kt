package com.example.MainViewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.badgetsdomain.Budgets
import com.example.domain.usecases.GetShareDetailsUseCase
import com.example.money.core.SingleLiveEvent
import com.example.money.rx.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {


    protected val compositeDisposable = CompositeDisposable()

    companion object {
        public val loading = SingleLiveEvent<Boolean>()
        public val Title = SingleLiveEvent<String>()
    }



    override fun onCleared() {
        if (compositeDisposable != null) {
            compositeDisposable.clear()
            compositeDisposable.dispose()
        }
    }

}