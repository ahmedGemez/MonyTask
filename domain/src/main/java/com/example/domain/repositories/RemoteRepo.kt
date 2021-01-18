package com.example.domain.repositories

import com.example.domain.models.badgetsdomain.Budgets
import com.example.domain.models.budgetdetails.BudgetDetails
import com.example.domain.models.createaccount.CreateAccountRequest
import com.example.domain.models.createaccount.response.AccountData
import com.example.domain.models.payeetransactions.PayeeTransactions
import io.reactivex.Single

interface RemoteRepo {


    fun getBudges(): Single<Budgets>
    fun createAccount(RequestBody: CreateAccountRequest, bugetId:String?): Single<AccountData>
    fun getBudgetDetails( bugetId: String?): Single<BudgetDetails>
    fun getPayeeTransactions( bugetId: String?,payeeId: String?): Single<PayeeTransactions>



}