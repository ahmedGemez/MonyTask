package com.example.money.features.budgets

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.MainViewModel.BaseViewModel
import com.example.domain.models.badgetsdomain.Budget
import com.example.domain.models.badgetsdomain.Budgets
import com.example.domain.usecases.GetShareDetailsUseCase
import com.example.money.core.ErrorHandling
import com.example.money.rx.SchedulersProvider
import com.example.money.R
import com.example.money.core.SingleLiveEvent
import javax.inject.Inject

class BudgetsViewModel @Inject constructor(
    val shareUseCase: GetShareDetailsUseCase,
    val schedulers: SchedulersProvider,
    val errorHandling: ErrorHandling,
    val context: Context
) : BaseViewModel() {

    private val _dataLoading = MutableLiveData<Budgets>()
    val openBudgetEvent = SingleLiveEvent<String>()


    fun getBudgets(){
        shareUseCase.getBudges()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe({loading.value = true})
            .doFinally({loading.value = false})
            .subscribe({
                it?.let {
                    _dataLoading.value = it
                }
            },{
                Log.d("error",it.message+"")
                errorHandling.accept(it,context.getString(R.string.error))
            }).let {
                compositeDisposable.add(it)
            }
    }


    private val _items: LiveData<List<Budget>> = _dataLoading.switchMap {
        getBudgetList(it)
    }

    val items: LiveData<List<Budget>> =_items

    private fun getBudgetList(budgets: Budgets): LiveData<List<Budget>> {
        val result = MutableLiveData<List<Budget>>()
          result.value = budgets.data.budgets
        return result
    }

    fun openBudget(budgetId: String) {
        openBudgetEvent.value = budgetId
    }


}