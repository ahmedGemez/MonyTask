package com.example.money.di.components

import com.example.money.di.FragmentModule
import com.example.money.di.FragmentScope
import com.example.money.features.createaccount.CreateAccountFragment
import com.example.money.features.budgetdetails.BudgetDetailsFragment
import com.example.money.features.budgets.BudgetsFragment
import com.example.money.features.payeetransactions.PayeeTransactionsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ FragmentModule::class])
interface FragmentComponents {

    fun inject(BudgetsFragment: BudgetsFragment?)
    fun budgetDetailsFragment(budgetDetailsFragment: BudgetDetailsFragment?)
    fun createAccountFragment(createAccountFragment: CreateAccountFragment?)
    fun payeeTransactiontFragment(payeeTransactionsFragment: PayeeTransactionsFragment?)

    @Subcomponent.Builder
    interface Builder {
        fun build(): FragmentComponents
    }

}