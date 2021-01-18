package com.example.domain.models.createaccount.response


import com.google.gson.annotations.SerializedName

data class AccountData(
    @SerializedName("data")
    val `data`: Data
)