package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("Version")
data class Version(


    @PrimaryKey(autoGenerate = true) val verId: Int,
    @SerializedName("eq_version") val EqVersion: Int,
    @SerializedName("fare_version") val FareVersion: Int,
    @SerializedName("pass_version") val PassVersion: Int
)
