package com.example.money.features.createaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.money.MainActivity
import com.example.money.MoneyApp
import com.example.money.R
import com.example.money.databinding.FragmentCreateAccountBinding
import com.example.money.di.ViewModelFactory
import com.example.money.di.components.AppComponent
import com.example.money.di.components.FragmentComponents
import com.example.money.features.budgetdetails.BudgetDetailsFragmentArgs
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class CreateAccountFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CreateAccountViewModel
    private lateinit var fragmentCreateAccountBinding: FragmentCreateAccountBinding

    private val args: BudgetDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var appComponent: AppComponent = (requireActivity().application as MoneyApp).appComponent
        var FragmentComponents: FragmentComponents = appComponent.getFragmentComponentBuilder().build()
        FragmentComponents.createAccountFragment(this)
        (activity as MainActivity).titlePage.text = getString(R.string.create_account)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get<CreateAccountViewModel>(
            CreateAccountViewModel::class.java)
        fragmentCreateAccountBinding =
            FragmentCreateAccountBinding.inflate(inflater, container, false).apply {
                budgetid = args.BudgetId
                viewmodel = viewModel
            }

        viewModel.setBudgetId(args.BudgetId)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, viewModel.types
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fragmentCreateAccountBinding.types?.adapter = adapter
        fragmentCreateAccountBinding.types.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.type.value = viewModel.types[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        observeSccess()
        return fragmentCreateAccountBinding.root
    }


    fun observeSccess(){
        viewModel.success.observe(this, Observer {
            Toast.makeText(requireContext(),getString(R.string.success_Created),Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        })
    }


}