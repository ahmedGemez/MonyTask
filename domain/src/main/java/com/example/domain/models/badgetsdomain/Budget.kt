package com.example.domain.models.badgetsdomain


import com.google.gson.annotations.SerializedName

data class Budget(
    @SerializedName("currency_format")
    val currencyFormat: CurrencyFormat,
    @SerializedName("date_format")
    val dateFormat: DateFormat,
    @SerializedName("first_month")
    val firstMonth: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_modified_on")
    val lastModifiedOn: String,
    @SerializedName("last_month")
    val lastMonth: String,
    @SerializedName("name")
    val name: String
)