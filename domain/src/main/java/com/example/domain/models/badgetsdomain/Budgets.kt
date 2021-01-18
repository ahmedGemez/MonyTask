package com.example.domain.models.badgetsdomain


import com.google.gson.annotations.SerializedName

data class Budgets(
    @SerializedName("data")
    val `data`: Data
)