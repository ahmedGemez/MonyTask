package com.example.money.features.budgetdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.badgetsdomain.Budget
import com.example.domain.models.budgetdetails.Account
import com.example.domain.models.budgetdetails.BudgetDetails
import com.example.money.databinding.AccountItemBinding
import com.example.money.databinding.BudgetItemBinding



class AccountsAdapter(private val viewModel: BudgetDetailsViewModel,private val budgetId:String) :
    ListAdapter<Account, AccountsAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item,budgetId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: AccountItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: BudgetDetailsViewModel, item: Account,budgetId:String) {
            binding.viewmodel = viewModel
            binding.argClick = ArgClick(budgetId,item.transferPayeeId)
            binding.account = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AccountItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}
