package com.example.domain.models.payeetransactions


import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("account_name")
    val accountName: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("approved")
    val approved: Boolean,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("cleared")
    val cleared: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("flag_color")
    val flagColor: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("import_id")
    val importId: String,
    @SerializedName("matched_transaction_id")
    val matchedTransactionId: String,
    @SerializedName("memo")
    val memo: String,
    @SerializedName("parent_transaction_id")
    val parentTransactionId: String,
    @SerializedName("payee_id")
    val payeeId: String,
    @SerializedName("payee_name")
    val payeeName: String,
    @SerializedName("transfer_account_id")
    val transferAccountId: String,
    @SerializedName("transfer_transaction_id")
    val transferTransactionId: String,
    @SerializedName("type")
    val type: String
)