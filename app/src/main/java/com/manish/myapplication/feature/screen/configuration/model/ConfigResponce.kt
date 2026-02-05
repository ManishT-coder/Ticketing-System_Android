package com.manish.myapplication.feature.screen.configuration.model

import com.google.gson.annotations.SerializedName
import com.manish.myapplication.common.database.entity.AcqParameters
import com.manish.myapplication.common.database.entity.Cardtype
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.database.entity.Fares
import com.manish.myapplication.common.database.entity.Passes
import com.manish.myapplication.common.database.entity.Stations
import com.manish.myapplication.common.database.entity.Users
import com.manish.myapplication.common.database.entity.Version

data class ConfigResponse(
    val status: Boolean,
    val msg: String? = null,
    val data: ConfigData? = null
) {
    data class ConfigData(

        @SerializedName("Cardtype")
        val cardtype: List<Cardtype> = emptyList(),

        @SerializedName("stations")
        val stations: List<Stations> = emptyList(),

        @SerializedName("Version")
        val Version: List<Version> = emptyList(),

        @SerializedName("Users")
        val Users: List<Users> = emptyList(),

        @SerializedName("Passes")
        val passes: List<Passes> = emptyList(),

        @SerializedName("Fares")
        val fares: List<Fares> = emptyList(),

        @SerializedName("ACQ")
        val ACQ: List<AcqParameters> = emptyList(),

        val config: Config?
    )
}