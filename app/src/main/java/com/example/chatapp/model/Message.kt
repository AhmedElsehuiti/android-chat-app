package com.example.chatapp.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Message(
    var id :String?=null,
    var content:String?=null,
    var dateTime:Long?=null,
    var senderName:String?=null,
    var senderId:String?=null,
    var roomId:String?=null,
    var roomName:String?=null
){

    fun formatDateTime():String{
        val date = Date(dateTime!!)
        val simpleDateFormat = SimpleDateFormat("hh:mm a",Locale.getDefault())
        return simpleDateFormat.format(date)

    }
    companion object{
        const val COLLECTION_NAME = "message"
    }
}
