package com.example.domain.models.createaccount


import com.google.gson.annotations.SerializedName

data class CreateAccountRequest(
    @SerializedName("account")
    val account: Account
)