package com.example.chatapp.database

import com.example.chatapp.model.AppUser
import com.example.chatapp.model.Message
import com.example.chatapp.model.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
fun getCollection(collectionName: String):CollectionReference{
    val db = Firebase.firestore
  return db.collection(collectionName)

}

fun  addUserToFireStore(user:AppUser ,
                        onSuccessListener:OnSuccessListener<Void>
                        ,onFailureListener: OnFailureListener){

  val userCollection =  getCollection(AppUser.COLLECTION_NAME)
   val userDocument= userCollection.document(user.id!!)
    userDocument.set(user)
        .addOnSuccessListener (onSuccessListener)
        .addOnFailureListener (onFailureListener)

}
fun signIn(uid:String,
           onSuccessListener:OnSuccessListener<DocumentSnapshot>,
           onFailureListener: OnFailureListener){

    val collectionRef = getCollection(AppUser.COLLECTION_NAME)
    collectionRef.document(uid)
        .get()
        .addOnSuccessListener (onSuccessListener)
        .addOnFailureListener (onFailureListener)
}

fun addRoom(room: Room ,
            onSuccessListener:OnSuccessListener<Void>,
            onFailureListener: OnFailureListener){
    val coll = getCollection(Room.COLLECTION_NAME)
   val doc =  coll.document()
    room.id  = doc.id
    doc.set(room)
        .addOnSuccessListener (onSuccessListener)
        .addOnFailureListener (onFailureListener)

}
fun getRoom( onSuccessListener:OnSuccessListener<QuerySnapshot>,
             onFailureListener: OnFailureListener){
    val collection= getCollection(Room.COLLECTION_NAME)
    collection.get()
        .addOnSuccessListener (onSuccessListener)
        .addOnFailureListener  (onFailureListener)

}
fun getMessageRef(roomId:String):CollectionReference{
    val collectionRef = getCollection(Room.COLLECTION_NAME)
    val roomRef=collectionRef.document(roomId)
    return  roomRef.collection(Message.COLLECTION_NAME)
}
fun addMessage(message: Message,onSuccessListener:OnSuccessListener<Void>,
               onFailureListener: OnFailureListener){

    val messageCollRef =  getMessageRef(message.roomId!!)
    val messageRef = messageCollRef.document()
    message.id = messageRef.id
    messageRef.set(
        message
    )
        .addOnSuccessListener  (onSuccessListener)
        .addOnFailureListener  (onFailureListener)

}