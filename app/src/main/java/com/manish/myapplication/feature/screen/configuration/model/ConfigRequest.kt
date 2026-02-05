package com.manish.myapplication.feature.screen.configuration.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigRequest(

    val ip: String,

    @SerializedName("eq_type_id")
    @ColumnInfo("eq_type_id") val eqtypeId: Int
)