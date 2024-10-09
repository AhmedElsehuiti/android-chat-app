package com.example.chatapp.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.DataUtils
import com.example.chatapp.R
import com.example.chatapp.databinding.ItemMasssageRecievedBinding
import com.example.chatapp.databinding.ItemMasssageSendBinding
import com.example.chatapp.model.Message

class MessageAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items= mutableListOf<Message?>()
    val RECVIEVED = 1
    val SEND = 2
    class SendMessageViewHolder(val viewDataBinding: ItemMasssageSendBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){
            fun bind(message:Message?){
                viewDataBinding.message = message
                viewDataBinding.executePendingBindings()

            }

    }
    class ReceivedMessageViewHolder(val viewDataBinding:ItemMasssageRecievedBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root){
        fun bind(message:Message?){
            viewDataBinding.message = message
            viewDataBinding.executePendingBindings()

        }
    }

    override fun getItemViewType(position: Int): Int {
        val message= items[position]
        if (message?.senderId==DataUtils.user?.id){
            return SEND
        }
        return RECVIEVED


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (RECVIEVED==viewType){
            val itemBinding:ItemMasssageRecievedBinding=
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_masssage_recieved,parent,false)
            return ReceivedMessageViewHolder(itemBinding)
        }
        val itemBinding:ItemMasssageSendBinding=
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_masssage_send,parent,false)
        return SendMessageViewHolder(itemBinding)


    }

    override fun getItemCount(): Int {
       return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type=getItemViewType(position)
        if (holder is SendMessageViewHolder){
            holder.bind(items.get(position))

        }else if (holder is ReceivedMessageViewHolder){
            holder.bind(items.get(position))

        }
//        if (type==RECVIEVED){
//
//        }else if (type==SEND){
//
//        }
    }

    fun appendMessage(newMessagesList: MutableList<Message>) {
        items.addAll(newMessagesList)
        notifyItemRangeChanged(items.size+1,newMessagesList.size)

    }
}