package com.example.chatapp.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import com.example.chatapp.BaseViewModel
import com.example.chatapp.DataUtils
import com.example.chatapp.database.addMessage
import com.example.chatapp.model.Message
import com.example.chatapp.model.Room
import java.util.Date

class ChatViewModel : BaseViewModel<Navigator>() {
    val messageField=ObservableField<String>()
    val toastLiveData =MediatorLiveData<String>()

    var room:Room?=null
    fun massageSend(){
        val message = Message(
            content = messageField.get(),
            roomId = room?.id,
            senderId = DataUtils.user?.id,
            senderName = DataUtils.user?.firstName,
            dateTime = Date().time


        )
     addMessage(message,{
         messageField.set("")
     },{
       toastLiveData.value = "Something went wrong "

     })





    }
}
