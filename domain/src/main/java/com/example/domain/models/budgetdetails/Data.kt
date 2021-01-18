package com.example.domain.models.budgetdetails


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("accounts")
    val accounts : List<Account>,
    @SerializedName("server_knowledge")
    val serverKnowledge: Int
)