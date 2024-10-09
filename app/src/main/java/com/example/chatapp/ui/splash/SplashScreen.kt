package com.example.chatapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.DataUtils

import com.example.chatapp.R
import com.example.chatapp.database.signIn
import com.example.chatapp.model.AppUser
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.login.LoginActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper())
            .postDelayed({
                checkLoggedINUser()

            },2000) }

    private fun checkLoggedINUser() {
        val firebaseUser = Firebase.auth.currentUser
        if (firebaseUser==null){
            startLoginActivity()

        }
        else{
            signIn(firebaseUser.uid,
                {doc->
            val user= doc.toObject(AppUser::class.java)
                    DataUtils.user =user



            },{
                startHomeActivity()
                Toast.makeText(this,"can not find user",Toast.LENGTH_LONG).show()
                })
            startHomeActivity()
        }

    }

    private fun startHomeActivity() {
        startActivity(Intent(this,HomeActivity::class.java))
    }

    private fun startLoginActivity() {
        startActivity(Intent(this,LoginActivity::class.java))

    }
}