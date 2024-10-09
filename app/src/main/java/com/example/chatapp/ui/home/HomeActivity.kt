package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.BaseActivity
import com.example.chatapp.Constants

import com.example.chatapp.R
import com.example.chatapp.database.getRoom
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.model.Room
import com.example.chatapp.ui.addRoom.AddRoomActivity
import com.example.chatapp.ui.chat.ChatActivity

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(),Navigator {
    val adapter = RoomsAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator =this
        viewDataBinding.recyclerView.adapter =adapter
        initRecyclerView()


        }
        fun initRecyclerView(){
            adapter.onItemClickListener =object : RoomsAdapter.OnItemClickListener{
                override fun onItemClick(pos: Int, room: Room) {
                    startChatActivity(room)

                }
        }


    }

    private fun startChatActivity(room: Room) {
        val intent = Intent(this , ChatActivity::class.java)
        intent.putExtra(Constants.EXTRA_ROOM,room)
        startActivity(intent)


    }

    override fun onStart() {
        super.onStart()
        getRoom({querySnapshot->
            val rooms = querySnapshot.toObjects(Room::class.java)
//            val roomList = mutableListOf<Room?>()
//                querySnapshot.documents.forEach{doc->
//                    val room =doc.toObject(Room::class.java)
//                    roomList.add(room)
//
//                }
            adapter.changeData(rooms)

        },{
            Toast.makeText(this,"can not fetch room",Toast.LENGTH_LONG).show()
        })
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun gToAddRoom() {
        startActivity(Intent(this, AddRoomActivity::class.java))
    }
}