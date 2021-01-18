package com.example.domain.models.badgetsdomain


import com.google.gson.annotations.SerializedName

data class CurrencyFormat(
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("decimal_digits")
    val decimalDigits: Int,
    @SerializedName("decimal_separator")
    val decimalSeparator: String,
    @SerializedName("display_symbol")
    val displaySymbol: Boolean,
    @SerializedName("example_format")
    val exampleFormat: String,
    @SerializedName("group_separator")
    val groupSeparator: String,
    @SerializedName("iso_code")
    val isoCode: String,
    @SerializedName("symbol_first")
    val symbolFirst: Boolean
)