package com.example.domain.models.createaccount.response


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("balance")
    val balance: Int,
    @SerializedName("cleared_balance")
    val clearedBalance: Int,
    @SerializedName("closed")
    val closed: Boolean,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("note")
    val note: Any,
    @SerializedName("on_budget")
    val onBudget: Boolean,
    @SerializedName("transfer_payee_id")
    val transferPayeeId: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uncleared_balance")
    val unclearedBalance: Int
)