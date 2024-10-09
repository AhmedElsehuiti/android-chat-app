package com.example.chatapp.ui.register

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.BaseViewModel
import com.example.chatapp.DataUtils
import com.example.chatapp.database.addUserToFireStore
import com.example.chatapp.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class RegisterViewModel : BaseViewModel<Navigator>() {
    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val firstNameError = ObservableField<String>()
    val lastNameError = ObservableField<String>()
    val userNameError = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()



    val auth = Firebase.auth
    fun createAccount(){
       if (validate()){
           addAccountToFirebase()

        }

    }
    fun addAccountToFirebase(){
        showLoading.value=true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener {task->
                if (!task.isSuccessful){
                    showLoading.value=false
                    messageLiveData.value = task.exception?.localizedMessage
                    Log.e("firebase","failed"
                    +task.exception?.localizedMessage)

                }else{
                   // messageLiveData.value = "Successful register"
                    showLoading.value=false
                   // Log.e("firebase","success registration")
                    createFireStoreUser(task.result.user?.uid)

                }

            }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun createFireStoreUser(uid:String?) {
        showLoading.value =true
        val user = AppUser(
            id = uid,
            firstName = firstName.get(),
            userName = userName.get(),
            lastName = lastName.get(),
            enail = email.get()



        )
        addUserToFireStore(user,
            {
            showLoading.value= false
                DataUtils.user=user
                navigator?.openHomeScreen()


        },
            {
                showLoading.value=false
                messageLiveData.value=it.localizedMessage


        })

    }

    fun validate():Boolean{
        var valid = true
        if (firstName.get().isNullOrBlank()){
            firstNameError.set("this filed is required")
            valid =false
        }
        else{
            firstNameError.set(null)
        }
        if (lastName.get().isNullOrBlank()){
            lastNameError.set("this filed is required")
            valid =false
        }
        else{
            lastNameError.set(null)
        }
        if (userName.get().isNullOrBlank()){
            userNameError.set("this filed is required")
            valid =false
        }
        else{
            userNameError.set(null)
        }
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