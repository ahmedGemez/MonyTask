package com.example.domain.models.badgetsdomain


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("budgets")
    val budgets: List<Budget>,
    @SerializedName("default_budget")
    val defaultBudget: Any
)