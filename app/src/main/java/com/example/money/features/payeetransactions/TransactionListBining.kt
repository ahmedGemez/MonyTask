package com.example.money.features.payeetransactions

import com.example.domain.models.payeetransactions.Transaction
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


/**
 * [BindingAdapter]s for the [Task]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Transaction>?) {
    items?.let {
        (listView.adapter as PayeeTransactionAdapter).submitList(items)
    }
}






