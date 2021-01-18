package com.example.domain.models.createaccount.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("account")
    val account: Account,
    @SerializedName("server_knowledge")
    val serverKnowledge: Int
)