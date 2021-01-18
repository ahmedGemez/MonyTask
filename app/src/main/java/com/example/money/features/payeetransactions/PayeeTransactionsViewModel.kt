package com.example.money.features.payeetransactions

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.MainViewModel.BaseViewModel
import com.example.domain.models.badgetsdomain.Budget
import com.example.domain.models.badgetsdomain.Budgets
import com.example.domain.models.payeetransactions.PayeeTransactions
import com.example.domain.models.payeetransactions.Transaction
import com.example.domain.usecases.GetShareDetailsUseCase
import com.example.money.R
import com.example.money.core.ErrorHandling
import com.example.money.rx.SchedulersProvider
import javax.inject.Inject

class PayeeTransactionsViewModel @Inject constructor(
    val shareUseCase: GetShareDetailsUseCase,
    val schedulers: SchedulersProvider,
    val errorHandling: ErrorHandling,
    val context: Context
) : BaseViewModel() {

    private val _dataLoading = MutableLiveData<PayeeTransactions>()


    fun getPayeeTransactions(budgetId: String?,payeeId: String?) {
        shareUseCase.getPayeeTransactions(budgetId,payeeId)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe({ loading.value = true })
            .doFinally({ loading.value = false })
            .subscribe({
                it.let {
                    _dataLoading.value = it
                }
                //  accountList.value= it.data.accounts.filter { account ->account.deleted==false }
            }, {
                Log.d("error", it.message + "")
                errorHandling.accept(it, context.getString(R.string.error))
            }).let {
                compositeDisposable.add(it)
            }
    }

    private val _items: LiveData<List<Transaction>> = _dataLoading.switchMap {
        getTransactionsList(it)
    }
    val items: LiveData<List<Transaction>> =_items

    private fun getTransactionsList(transactions: PayeeTransactions): LiveData<List<Transaction>> {
        val result = MutableLiveData<List<Transaction>>()
        result.value = transactions.data.transactions
        return result
    }


}