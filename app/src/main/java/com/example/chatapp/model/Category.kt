package com.example.chatapp.model

import android.media.Image
import com.example.chatapp.R

data class Category (
    val id :String?=null,
    val name:String?=null,
    val imageId :Int?=null
){
    companion object{
       const  val MUSIC = "music"
       const val SPORT = "sports"
        const val MOVIES ="movies"


        fun fromId(catId:String):Category{
            when(catId){
                MUSIC->{
                    return Category(
                        MUSIC,
                        name = "music",
                        imageId = R.drawable.music

                    )
                }
                SPORT->{
                    return Category(
                        SPORT,
                        name = "sports",
                        imageId = R.drawable.sport

                    )
                }else -> {
                return Category(
                    MOVIES,
                    name = "movies",
                    imageId = R.drawable.movies
                )
                }


            }

        }
        fun getCategoriesList():List<Category>{
            return listOf(
                fromId(MUSIC),
                fromId(SPORT),
                fromId(MOVIES)


                )
        }

    }

}
