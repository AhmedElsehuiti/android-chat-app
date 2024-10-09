package com.example.chatapp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.BaseActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityResgisterBinding
import com.example.chatapp.ui.home.HomeActivity

class RegisterActivity : BaseActivity<ActivityResgisterBinding, RegisterViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this


    }

    override fun getLayoutId(): Int {
        return  R.layout.activity_resgister

    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun openHomeScreen() {
        startActivity(Intent(this, HomeActivity::class.java))

    }

}