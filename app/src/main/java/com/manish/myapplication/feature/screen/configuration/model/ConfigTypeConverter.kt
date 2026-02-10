package com.manish.myapplication.feature.screen.configuration.model


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConfigTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromConfigData(data: ConfigResponse.ConfigData?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toConfigData(json: String?): ConfigResponse.ConfigData? {
        if (json == null) return null
        val type = object : TypeToken<ConfigResponse.ConfigData>() {}.type
        return gson.fromJson(json, type)
    }
}