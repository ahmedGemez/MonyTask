package com.example.domain.models.payeetransactions


import com.google.gson.annotations.SerializedName

data class PayeeTransactions(
    @SerializedName("data")
    val `data`: Data
)