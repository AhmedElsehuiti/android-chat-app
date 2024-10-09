package com.example.chatapp

import android.annotation.SuppressLint
import com.example.chatapp.model.AppUser
import com.google.firebase.firestore.auth.User

object DataUtils {
    var user:AppUser?=null
    @SuppressLint("RestrictedApi")
    var firebaseUser : User? =null
}