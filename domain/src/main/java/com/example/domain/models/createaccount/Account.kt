package com.example.domain.models.createaccount


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("balance")
    val balance: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String?
)