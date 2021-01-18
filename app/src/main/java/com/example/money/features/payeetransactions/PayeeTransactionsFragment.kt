package com.example.money.features.payeetransactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.money.MainActivity
import com.example.money.MoneyApp
import com.example.money.R
import com.example.money.databinding.FragmentCreateAccountBinding
import com.example.money.databinding.FragmentPayeeTransactionsBinding
import com.example.money.di.ViewModelFactory
import com.example.money.di.components.AppComponent
import com.example.money.di.components.FragmentComponents
import com.example.money.features.budgetdetails.BudgetDetailsFragmentArgs
import com.example.money.features.createaccount.CreateAccountViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class PayeeTransactionsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PayeeTransactionsViewModel
    private lateinit var fragmentPayeeTransactionsBinding: FragmentPayeeTransactionsBinding

    private val args: PayeeTransactionsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var appComponent: AppComponent = (requireActivity().application as MoneyApp).appComponent
        var FragmentComponents: FragmentComponents = appComponent.getFragmentComponentBuilder().build()
        FragmentComponents.payeeTransactiontFragment(this)
        (activity as MainActivity).titlePage.text = getString(R.string.payee_transactions)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get<PayeeTransactionsViewModel>(
            PayeeTransactionsViewModel::class.java)
        fragmentPayeeTransactionsBinding =
            FragmentPayeeTransactionsBinding.inflate(inflater, container, false).apply {
                viewmodel = viewModel
            }

        viewModel.getPayeeTransactions(args.budgetId,args.transferPayeeId)

        return fragmentPayeeTransactionsBinding.root
    }

}