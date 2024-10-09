package com.example.chatapp.ui.chat

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.BaseActivity
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.example.chatapp.database.getMessageRef
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.model.Message
import com.example.chatapp.model.Room
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query


class ChatActivity : BaseActivity<ActivityChatBinding , ChatViewModel>(),Navigator {
    val adapter = MessageAdapter()
    lateinit var layoutManager :LinearLayoutManager
    lateinit var room :Room
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        room = intent.getParcelableExtra<Room>(Constants.EXTRA_ROOM)!!
        layoutManager = LinearLayoutManager(this,)
        layoutManager.stackFromEnd=true
        viewDataBinding.recyclerviewMessage.layoutManager=layoutManager
        viewModel.room=room
        viewDataBinding.vm = viewModel
        viewModel.navigator=this
        viewDataBinding.recyclerviewMessage.adapter =adapter
        listenForMessagesUpdates()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat

    }
    fun listenForMessagesUpdates(){
        getMessageRef(room.id!!)
            .orderBy("dateTime",Query.Direction.ASCENDING)
            .addSnapshotListener{snapshot,exception->
                if (exception!=null){
                    Toast.makeText(this,"can not retrieve message",Toast.LENGTH_LONG).show()
                }
                else{
                    val newMessagesList = mutableListOf<Message>()
                    for (dc in snapshot!!.documentChanges){
                        when(dc.type){
                            DocumentChange.Type.ADDED->{
                              val message =   dc.document.toObject(Message::class.java)
                                newMessagesList.add(message)
                            }

                            DocumentChange.Type.MODIFIED -> TODO()
                            DocumentChange.Type.REMOVED -> TODO()
                        }
                    }
                    adapter.appendMessage(newMessagesList)
                   viewDataBinding.recyclerviewMessage.smoothScrollToPosition(adapter.itemCount)
                }

            }

    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this)[ChatViewModel::class.java]

    }

}