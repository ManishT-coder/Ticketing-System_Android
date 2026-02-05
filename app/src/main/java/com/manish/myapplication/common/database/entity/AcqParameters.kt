package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("AcqParameters")
data class AcqParameters(

    @PrimaryKey(autoGenerate = true) val AcqParaId: Int,

    @SerializedName("acq_param_id") val AcqParamId: Int,
    @SerializedName("operator_id") val OperatorID: String,
    @SerializedName("acq_id") val AcqId: String,
    @SerializedName("acq_name") val AcqName: String,
    @SerializedName("acq_mid") val AcqMid: String,
    @SerializedName("client_id") val ClientId: String,
    @SerializedName("line_id") val LineId: Int,
    @SerializedName("description") val Description: String
)