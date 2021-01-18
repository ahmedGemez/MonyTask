package com.example.data.apiservice

import com.example.data.apiservice.APIConstants.BUDGET_Details
import com.example.data.apiservice.APIConstants.CREATE_ACCOUNT
import com.example.data.apiservice.APIConstants.GET_BUDETS
import com.example.data.apiservice.APIConstants.PAY_TRANSACTIONS
import com.example.domain.models.badgetsdomain.Budgets
import com.example.domain.models.budgetdetails.BudgetDetails
import com.example.domain.models.createaccount.CreateAccountRequest
import com.example.domain.models.createaccount.response.AccountData
import com.example.domain.models.payeetransactions.PayeeTransactions
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface  ApiService {

    @GET(GET_BUDETS)
    fun getBudges(): Single<Budgets>

    @GET(BUDGET_Details)
    fun getBudgetDetails(@Path("budget_id")  budgetId:String?): Single<BudgetDetails>

    @POST(CREATE_ACCOUNT)
    fun createAccount(@Body requestBody: CreateAccountRequest, @Path("budget_id")  budgetId:String?): Single<AccountData>

    @GET(PAY_TRANSACTIONS)
    fun getPayeeTransactions( @Path("budget_id")  budgetId:String?, @Path("payee_id")  payeeId:String?): Single<PayeeTransactions>
}