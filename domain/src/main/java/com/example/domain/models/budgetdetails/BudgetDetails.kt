package com.example.domain.models.budgetdetails


import com.google.gson.annotations.SerializedName

data class BudgetDetails(
    @SerializedName("data")
    val `data`: Data
)