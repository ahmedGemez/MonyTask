package com.example.data.repos

import com.example.data.apiservice.ApiService
import com.example.domain.models.badgetsdomain.Budgets
import com.example.domain.models.budgetdetails.BudgetDetails
import com.example.domain.models.createaccount.CreateAccountRequest
import com.example.domain.models.createaccount.response.AccountData
import com.example.domain.models.payeetransactions.PayeeTransactions
import com.example.domain.repositories.RemoteRepo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService) : RemoteRepo {


    override fun getBudges(): Single<Budgets> {
       return apiService.getBudges()
    }

    override fun createAccount(RequestBody: CreateAccountRequest, bugetId: String?): Single<AccountData> {
        return apiService.createAccount(RequestBody,bugetId)
    }

    override fun getBudgetDetails( bugetId: String?): Single<BudgetDetails> {
        return apiService.getBudgetDetails(bugetId)
    }

    override fun getPayeeTransactions(bugetId: String?, payeeId: String?): Single<PayeeTransactions> {
        return apiService.getPayeeTransactions(bugetId,payeeId)
    }


}