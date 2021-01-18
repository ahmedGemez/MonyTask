package com.example.money.features.createaccount

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.MainViewModel.BaseViewModel
import com.example.domain.models.createaccount.Account
import com.example.domain.models.createaccount.CreateAccountRequest
import com.example.domain.usecases.GetShareDetailsUseCase
import com.example.money.R
import com.example.money.core.ErrorHandling
import com.example.money.core.SingleLiveEvent
import com.example.money.rx.SchedulersProvider
import javax.inject.Inject

class CreateAccountViewModel @Inject constructor(
    val shareUseCase: GetShareDetailsUseCase,
    val schedulers: SchedulersProvider,
    val errorHandling: ErrorHandling,
    val context: Context
) : BaseViewModel() {

    var types: List<String> = mutableListOf("checking", "checking", "savings", "creditCard", "cash")
    private var budgetId: String? = null
    val items = arrayListOf("checking", "checking1")
    val name = MutableLiveData<String>()
    val type = MutableLiveData<String>()
    val balance = MutableLiveData<String>()

    val success = SingleLiveEvent<Boolean>()

    fun createAccount(body: CreateAccountRequest, budgetId: String?) {
        shareUseCase.createAccount(body, budgetId)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe({ loading.value = true })
            .doFinally({ loading.value = false })
            .subscribe({
                    success.value=true
            }, {
                Log.d("error", it.message + "")
                errorHandling.accept(it, context.getString(R.string.error))
            }).let {
                compositeDisposable.add(it)
            }
    }

    fun setBudgetId(budgetId: String) {
        this.budgetId = budgetId
    }

    fun addAccount() {
        val currentTitle = name.value
        val currentbalance = balance.value
        if (currentTitle == null || currentbalance == null) {
            Toast.makeText(context, "please fill all fields", Toast.LENGTH_LONG).show()
            return
        }

        val currentbudgetId = budgetId
        var Account: Account = Account(currentbalance.toInt(), currentTitle,type.value )

        var createBody: CreateAccountRequest = CreateAccountRequest(Account)
        createAccount(createBody, currentbudgetId)
    }


}
