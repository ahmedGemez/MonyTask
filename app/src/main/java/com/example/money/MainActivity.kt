package com.example.money

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.MainViewModel.BaseViewModel
import com.example.money.databinding.ActivityMainBinding
import com.example.money.di.ViewModelFactory
import com.example.money.di.components.AppComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel : BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var appComponent: AppComponent =    (application as MoneyApp).appComponent
        appComponent.inject(this)


        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get<BaseViewModel>(BaseViewModel::class.java)

        setupToolBar()

    }

    private fun setupToolBar(){
        observeLoading()
        observeTitle()
    }
    fun observeLoading(){
        BaseViewModel.loading.observe(this, Observer {
            it?.let {
                if(it){
                    progressBar.visibility = View.VISIBLE
                }else{
                    progressBar.visibility = View.GONE

                }
            }
        })
    }

    fun observeTitle(){
        BaseViewModel.Title.observe(this, Observer {
            it?.let {
                titlePage.text = it
            }
        })
    }
}