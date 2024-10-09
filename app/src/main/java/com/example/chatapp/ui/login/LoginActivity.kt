package com.example.chatapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.BaseActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.register.RegisterActivity

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator=this
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_login

    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun openHomeScreen() {
        startActivity(Intent(this,HomeActivity::class.java))
    }

    override fun openRegisteration() {
        startActivity(Intent(this,RegisterActivity::class.java))
    }


}