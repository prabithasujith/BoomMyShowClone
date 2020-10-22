package com.prabitha.kotlin.boommyshowclone.dataremote.local.typeConverter


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prabitha.kotlin.boommyshowclone.dataremote.local.entity.Movie
import java.lang.reflect.Type

object MovieTypeConverter {

    @TypeConverter
    @JvmStatic
    fun fromList(value: List<Movie>) = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun toList(value: String): List<Movie> {
        val listType: Type = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(value, listType)
    }

}