package com.example.chatapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.model.Category
import com.google.firestore.admin.v1.Index

class CategoriesSpinnerAdapter(val items:List<Category>) :BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(index: Int): Any {
        return items[index]
    }

    override fun getItemId(index: Int): Long {
        return 0
    }

    override fun getView(index: Int, view: View?, container: ViewGroup?): View {
        var myView =view
        var viewHolder:ViewHolder
        if (myView==null){
            myView =LayoutInflater.from(container!!.context)
                .inflate(R.layout.item_spinner_category,container,false)
            viewHolder = ViewHolder(myView)
            myView.tag = viewHolder

        }else{
            viewHolder = myView.tag as ViewHolder

        }
        val item = items[index]
        viewHolder.title.setText(item.name)
        viewHolder.image.setImageResource(item.imageId!!)
        return myView!!

    }
    class ViewHolder(val view: View){
        val title :TextView = view.findViewById(R.id.title)
        val image :ImageView = view.findViewById(R.id.image_category)



    }
}