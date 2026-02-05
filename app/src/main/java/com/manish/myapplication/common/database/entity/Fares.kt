package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("Fares")
data class Fares(

    @PrimaryKey(autoGenerate = true) val Fareid: Int,
    @SerializedName("fare_table_id") val FareTableID: Int,
    @SerializedName("source_id") val SourceId: Int,
    @SerializedName("destination_id") val DestinationId: Int,
    @SerializedName("fare") val Fare: Int
)