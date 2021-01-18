package com.example.money.features.budgets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.money.MainActivity
import com.example.money.MoneyApp
import com.example.money.R
import com.example.money.databinding.FragmentBudgetsBinding
import com.example.money.di.ViewModelFactory
import com.example.money.di.components.AppComponent
import com.example.money.di.components.FragmentComponents
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BudgetsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BudgetsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel : BudgetsViewModel
    private lateinit var listAdapter: BudgetsAdapter
    private lateinit var fragmentBudgetsBinding: FragmentBudgetsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var appComponent: AppComponent =    (requireActivity().application as MoneyApp).appComponent
        var FragmentComponents: FragmentComponents =appComponent.getFragmentComponentBuilder().build()
        FragmentComponents.inject(this)

        (activity as MainActivity).titlePage.text = getString(R.string.budgets_list)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get<BudgetsViewModel>(BudgetsViewModel::class.java)


        fragmentBudgetsBinding = FragmentBudgetsBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        viewModel.getBudgets()
        observeOpenBudget()

        return fragmentBudgetsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Set the lifecycle owner to the lifecycle of the view
        fragmentBudgetsBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()

    }

    private fun setupListAdapter() {
        if (viewModel != null) {
            listAdapter = BudgetsAdapter(viewModel)
            fragmentBudgetsBinding.BudgetList.adapter = listAdapter
        } else {
            Log.d("FragmentBudgetsBinding","ViewModel not initialized when attempting to set up adapter.")
        }
    }


    fun observeOpenBudget(){
        viewModel.openBudgetEvent.observe(this, Observer {
            it?.let {
                openBudgetDetails(it)
            }
        })
    }
    private fun openBudgetDetails(BudgetId: String) {
        val currentFragment = NavHostFragment.findNavController(this).currentDestination?.id
        if(currentFragment == R.id.homeFragment){
            val action   =  BudgetsFragmentDirections.actionHomeFragmentToBudgetDetailsFragment(BudgetId);
            findNavController().navigate(action)
        }
    }


}