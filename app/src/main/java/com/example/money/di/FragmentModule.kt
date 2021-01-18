package com.example.money.di

import androidx.lifecycle.ViewModel
import com.example.money.features.createaccount.CreateAccountViewModel
import com.example.money.features.budgetdetails.BudgetDetailsViewModel
import com.example.money.features.budgets.BudgetsViewModel
import com.example.money.features.payeetransactions.PayeeTransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentModule {

 @Binds
 @IntoMap
 @ViewModelKey(BudgetsViewModel::class)
 abstract fun bindBudgetsViewModel(viewModel: BudgetsViewModel): ViewModel
 @Binds
 @IntoMap
 @ViewModelKey(BudgetDetailsViewModel::class)
 abstract fun bindBudgetDetailsViewModel(viewModel: BudgetDetailsViewModel): ViewModel

 @Binds
 @IntoMap
 @ViewModelKey(CreateAccountViewModel::class)
 abstract fun bindCreateAccountViewModel(viewModel: CreateAccountViewModel): ViewModel
 @Binds
 @IntoMap
 @ViewModelKey(PayeeTransactionsViewModel::class)
 abstract fun bindPayeeTransactionsViewModel(viewModel: PayeeTransactionsViewModel): ViewModel
}