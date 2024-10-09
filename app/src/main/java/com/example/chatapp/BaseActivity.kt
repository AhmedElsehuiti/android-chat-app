package com.example.chatapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


open abstract class BaseActivity<DB: ViewDataBinding , VM :BaseViewModel<*>> :AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var viewDataBinding: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel= initViewModel()
        subscribToLiveData()


    }

     fun subscribToLiveData() {
         viewModel.messageLiveData.observe(this) { message ->
             showDialog(message,"ok")

         }
         viewModel.showLoading.observe(this) { show ->
             if (show)
                 showLoading()
             else hideLoading()

         }
     }
    var alertDialog:AlertDialog?=null
    @SuppressLint("SuspiciousIndentation")
    fun showDialog(massage:String,
                   posActionName:String?=null,
                   posAction : DialogInterface.OnClickListener?=null,
                   nagActionName:String?=null,
                   nagAction : DialogInterface.OnClickListener?=null,
                   cancelable:Boolean=true){
        val defAction = DialogInterface.OnClickListener { dialog, _ -> dialog?.dismiss() }
        val builder = AlertDialog.Builder(this)
            .setMessage(massage)
        if (posActionName!=null)
        builder.setPositiveButton(posActionName,
            posAction ?: defAction
        )
        if (nagActionName!=null)
            builder.setNegativeButton(nagActionName,nagAction?:defAction)
        builder.setCancelable(cancelable)
       alertDialog= builder.show()
    }
    fun hideAlertDiaLog(){
        alertDialog?.dismiss()
        alertDialog=null
    }
    var progressDialog :ProgressDialog?=null
    fun showLoading(){
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading......")
        progressDialog?.setCancelable(false)
        progressDialog?.show()

    }
    fun hideLoading(){
        progressDialog?.dismiss()
    }

    abstract fun getLayoutId():Int
    abstract fun initViewModel():VM


}