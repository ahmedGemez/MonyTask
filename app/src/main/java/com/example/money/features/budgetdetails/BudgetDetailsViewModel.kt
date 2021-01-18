package com.example.money.features.budgetdetails

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.MainViewModel.BaseViewModel
import com.example.domain.models.budgetdetails.Account
import com.example.domain.models.budgetdetails.BudgetDetails
import com.example.domain.usecases.GetShareDetailsUseCase
import com.example.money.R
import com.example.money.core.ErrorHandling
import com.example.money.core.SingleLiveEvent
import com.example.money.rx.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BudgetDetailsViewModel @Inject constructor(
    val shareUseCase: GetShareDetailsUseCase,
    val schedulers: SchedulersProvider,
    val errorHandling: ErrorHandling,
    val context: Context
) : BaseViewModel() {


    val newAccountEvent = SingleLiveEvent<String>()
    val budgetDetails = MutableLiveData<BudgetDetails>()

    val _accountList = MutableLiveData<List<Account>>()
    val accountList: LiveData<List<Account>> = _accountList

    val openAccountEvent = SingleLiveEvent<ArgClick>()

    fun addNewAccount(budgetId: String) {
        newAccountEvent.value = budgetId
    }


    fun getbudgetDetails(budgetId: String?) {
        shareUseCase.getBudgetDetails(budgetId)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe({ loading.value = true })
            .doFinally({ loading.value = false })
            .subscribe({
                it.let {
                    budgetDetails.value = it
                    getFilteredlist(it.data.accounts)
                }
                //  accountList.value= it.data.accounts.filter { account ->account.deleted==false }
            }, {
                Log.d("error details request", it.message + "")
                errorHandling.accept(it, context.getString(R.string.error))
            }).let {
                compositeDisposable.add(it)
            }
    }


    fun getFilteredlist(requestDataList: List<Account>?) {
        val disposable =
            Observable.fromIterable<Account>(requestDataList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .filter { item: Account ->
                    (item.deleted == false)
                }
                .doOnNext { model: Account? -> }
                .toList()
                .subscribe({ list: List<Account>? ->
                    list?.sortedByDescending { account -> account.balance }
                    val sortedList = list?.sortedWith(compareByDescending({ it.balance }))
                    _accountList.value = sortedList
                }, {

                    Log.d("error filter", it.message + "")
                })
        compositeDisposable.add(disposable)
    }

    fun openAccount(arg: ArgClick) {
        openAccountEvent.value = arg
    }


}