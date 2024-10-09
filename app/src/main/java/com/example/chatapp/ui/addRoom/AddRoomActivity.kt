package com.example.chatapp.ui.addRoom

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.BaseActivity

import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityAddRoomBinding
import com.example.chatapp.databinding.ActivityResgisterBinding
import com.example.chatapp.model.Category
import com.example.chatapp.ui.CategoriesSpinnerAdapter

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {
    lateinit var adapter:CategoriesSpinnerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator =this

        adapter = CategoriesSpinnerAdapter(viewModel.categories)
        viewDataBinding.spinner.adapter =adapter
        viewDataBinding.spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedCategory = viewModel.categories[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        viewModel.roomAdded.observe(this) { added ->
            if (added) {
                showDialog("Room Added successfully",
                    posActionName  = "ok", posAction = DialogInterface.OnClickListener{
                            dialog: DialogInterface?, _: Int ->
                        dialog?.dismiss()
                        finish()
                    }, cancelable = false)
            }


        }


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this)[AddRoomViewModel::class.java]

    }
}