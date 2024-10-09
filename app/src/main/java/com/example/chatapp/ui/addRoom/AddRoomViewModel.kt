package com.example.chatapp.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import com.example.chatapp.BaseViewModel
import com.example.chatapp.database.addRoom
import com.example.chatapp.model.Category
import com.example.chatapp.model.Room
import com.example.chatapp.ui.CategoriesSpinnerAdapter

class AddRoomViewModel:BaseViewModel<Navigator>() {
    val name = ObservableField<String>()
    val description = ObservableField<String>()
    val nameError = ObservableField<String>()
    val descriptionError = ObservableField<String>()

    val roomAdded = MediatorLiveData<Boolean>()

    val categories = Category.getCategoriesList()
    var selectedCategory = categories[0]

    fun createRoom(){
        if (validate()){
            val room = Room(
                name  = name.get(),
                disc = description.get(),
                categoryId = selectedCategory.id



            )
            showLoading.value = true
            addRoom(room,{
                         showLoading.value=false
                roomAdded.value=true

            },{
                showLoading.value=false
                messageLiveData.value=it.localizedMessage

            })
        }

    }
    fun validate(): Boolean {
        var isValid =true
        if (name.get().isNullOrBlank()){
            nameError.set("required")
            isValid =false
        }
        else{
            nameError.set(null)
        }
        if (description.get().isNullOrBlank()){
            descriptionError.set("required")
            isValid =false
        }
        else{
            descriptionError.set(null)
        }
        return isValid

    }


}