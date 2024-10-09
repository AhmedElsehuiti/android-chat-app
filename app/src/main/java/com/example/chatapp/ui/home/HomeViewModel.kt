package com.example.chatapp.ui.home

import com.example.chatapp.BaseViewModel

class HomeViewModel : BaseViewModel<Navigator>() {
    fun createRoom(){
        navigator?.gToAddRoom()

    }
}