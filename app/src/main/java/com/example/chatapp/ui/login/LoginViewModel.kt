package com.example.chatapp.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableField
import com.example.chatapp.BaseViewModel
import com.example.chatapp.DataUtils
import com.example.chatapp.database.signIn
import com.example.chatapp.model.AppUser
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginViewModel : BaseViewModel<Navigator>() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val firebaseAuth = Firebase.auth


    fun login(){
        if (validate()){
            loginWithFirebaseAuth()

        }


    }
    fun openRegister(){
        navigator?.openRegisteration()

    }
    fun loginWithFirebaseAuth(){
        showLoading.value=true
        firebaseAuth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener {task->
                if (!task.isSuccessful){
                    showLoading.value=false
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("firebase","failed"
                            +task.exception?.localizedMessage)

                }else{
                    // messageLiveData.value = "Successful Login"
                  //  showLoading.value=false
                 //   Log.e("firebase","success registration")
                    //navigator?.openHomeScreen()
                    chickUserFromFireStore(task.result.user?.uid)
                }

            }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun chickUserFromFireStore(uid:String?) {
        showLoading.value=true
        signIn(uid!!,{docSnapshot->
            showLoading.value=false
            val user = docSnapshot.toObject(AppUser::class.java)
                     if (user==null){
                         messageLiveData.value = "Invalid email or password  "
                         return@signIn
                     }
            else{
                DataUtils.user = user
                navigator?.openHomeScreen()
                     }


        },{
            showLoading.value=false
            messageLiveData.value =it.localizedMessage

        })

    }

    fun validate():Boolean{
        var valid = true
        if (email.get().isNullOrBlank()){
            emailError.set("this filed is required")
            valid =false
        }
        else{
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()){
            passwordError.set("this filed is required")
            valid =false
        }
        else{
            passwordError.set(null)
        }

        return valid
    }



}