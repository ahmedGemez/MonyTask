package com.example.money.features.budgetdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.money.MainActivity
import com.example.money.MoneyApp
import com.example.money.R
import com.example.money.databinding.FragmentBudgetDetailsBinding
import com.example.money.databinding.FragmentBudgetsBinding
import com.example.money.di.ViewModelFactory
import com.example.money.di.components.AppComponent
import com.example.money.di.components.FragmentComponents
import com.example.money.features.budgetdetails.BudgetDetailsFragmentArgs
import com.example.money.features.budgets.BudgetsAdapter
import com.example.money.features.budgets.BudgetsFragmentDirections
import com.example.money.features.budgets.BudgetsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class BudgetDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel : BudgetDetailsViewModel
    private lateinit var fragmentBudgetDetailsBinding: FragmentBudgetDetailsBinding
    private lateinit var listAdapter: AccountsAdapter

    private val args: BudgetDetailsFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var appComponent: AppComponent =    (requireActivity().application as MoneyApp).appComponent
        var FragmentComponents: FragmentComponents =appComponent.getFragmentComponentBuilder().build()
        FragmentComponents.budgetDetailsFragment(this)
        (activity as MainActivity).titlePage.text = getString(R.string.budget_details)
        viewModel = ViewModelProvider(
                this,
                viewModelFactory
        ).get<BudgetDetailsViewModel>(BudgetDetailsViewModel::class.java)
        fragmentBudgetDetailsBinding = FragmentBudgetDetailsBinding.inflate(inflater, container, false).apply {
            budgetid = args.BudgetId
            viewmodel = viewModel
        }

        viewModel.getbudgetDetails(args.BudgetId)

        fragmentBudgetDetailsBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()

        observeAddAccount()
        observeOpenAccount()

        return fragmentBudgetDetailsBinding.root
    }



    private fun setupListAdapter() {
        if (viewModel != null) {
            listAdapter = AccountsAdapter(viewModel,args.BudgetId)
            fragmentBudgetDetailsBinding.accountList.adapter = listAdapter
        } else {
            Log.d("FragmentBudgetsBinding","ViewModel not initialized when attempting to set up adapter.")
        }
    }

    fun observeAddAccount(){
        viewModel.newAccountEvent.observe(this, Observer {
            it?.let {
                val currentFragment = NavHostFragment.findNavController(this).currentDestination?.id
                if(currentFragment == R.id.budgetDetailsFragment){
                    val action   =  BudgetDetailsFragmentDirections.actionBudgetDetailsFragmentToCreateAccountFragment(it);
                    findNavController().navigate(action)
                }
            }
        })
    }


    fun observeOpenAccount(){
        viewModel.openAccountEvent.observe(this, Observer {
            it?.let {
                openAccount(it)
            }
        })
    }
    private fun openAccount(argClick: ArgClick) {
        val currentFragment = NavHostFragment.findNavController(this).currentDestination?.id
        if(currentFragment == R.id.budgetDetailsFragment){
            val action   =  BudgetDetailsFragmentDirections.actionBudgetDetailsFragmentToPayeeTransactionsFragment(argClick.budgetId,argClick.payeeId)
            findNavController().navigate(action)
        }
    }

}