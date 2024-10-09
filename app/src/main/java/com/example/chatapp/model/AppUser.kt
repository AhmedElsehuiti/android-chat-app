package com.example.chatapp.model

data  class AppUser (
    var id:String?=null,
    var userName :String?=null,
    var firstName:String?=null,
    var lastName :String?=null,
    var enail:String?=null,
){
    companion object {
        const val COLLECTION_NAME = "users"
    }
}
