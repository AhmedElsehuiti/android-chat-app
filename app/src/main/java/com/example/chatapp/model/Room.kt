package com.example.chatapp.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import java.io.Serializable
import java.text.ParsePosition
@Parcelize
data class Room (
    var id:String?=null,
    var name:String?=null,
    var disc:String?=null,
    var categoryId: String?=null
):Parcelable {
    companion object{
        const val COLLECTION_NAME ="Rooms"
    }
    fun getCategoryImageId():Int?{
        return Category.fromId(categoryId?:Category.SPORT)
            .imageId
    }


}