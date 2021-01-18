package com.example.money.features.budgets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.badgetsdomain.Budget
import com.example.money.databinding.BudgetItemBinding



class BudgetsAdapter(private val viewModel: BudgetsViewModel) :
    ListAdapter<Budget, BudgetsAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: BudgetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: BudgetsViewModel, item: Budget) {
            binding.viewmodel = viewModel
            binding.budget = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BudgetItemBinding.inflate(layoutInflater, parent, false)

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
class TaskDiffCallback : DiffUtil.ItemCallback<Budget>() {
    override fun areItemsTheSame(oldItem: Budget, newItem: Budget): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Budget, newItem: Budget): Boolean {
        return oldItem == newItem
    }
}
