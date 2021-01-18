package com.example.data.apiservice

object APIConstants {

    const val BaseUrl = "https://api.youneedabudget.com/v1/"
    const val Token = "8769a722da1db3d430cbabd3c1356478252075c6699d3ef11e61aa367470a60f"


    const val GET_BUDETS = "budgets"
    const val CREATE_ACCOUNT = "budgets/{budget_id}/accounts"
    const val BUDGET_Details = "budgets/{budget_id}/accounts"
    const val PAY_TRANSACTIONS = "budgets/{budget_id}/payees/{payee_id}/transactions"



}