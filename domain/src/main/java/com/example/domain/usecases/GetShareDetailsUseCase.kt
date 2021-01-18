package com.example.domain.usecases

import com.example.domain.models.badgetsdomain.Budgets
import com.example.domain.models.budgetdetails.BudgetDetails
import com.example.domain.models.createaccount.CreateAccountRequest
import com.example.domain.models.createaccount.response.AccountData
import com.example.domain.models.payeetransactions.PayeeTransactions
import com.example.domain.repositories.RemoteRepo
import io.reactivex.Single
import javax.inject.Inject

class GetShareDetailsUseCase @Inject constructor(val apiRepo:RemoteRepo) {



     fun getBudges(): Single<Budgets> {
        return apiRepo.getBudges()
    }

     fun createAccount(RequestBody: CreateAccountRequest, bugetId: String?): Single<AccountData> {
        return apiRepo.createAccount(RequestBody,bugetId)
    }

     fun getBudgetDetails( bugetId: String?): Single<BudgetDetails> {
        return apiRepo.getBudgetDetails(bugetId)
    }

     fun getPayeeTransactions(bugetId: String?, payeeId: String?): Single<PayeeTransactions> {
        return apiRepo.getPayeeTransactions(bugetId,payeeId)
    }
}