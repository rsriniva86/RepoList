package com.shyam.repolist.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shyam.repolist.db.model.Repository

object DataConverter {
    val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun convertJsonStringToListItem(data: String?): List<Repository>? {
        // List
        val listType = object : TypeToken<List<Repository>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun convertListItemToJsonString(list: List<Repository>): String? {
        return gson.toJson(list)
    }
}