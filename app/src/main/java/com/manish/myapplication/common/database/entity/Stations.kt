package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("Stations")
data class Stations(

    @PrimaryKey(autoGenerate = true) val Stid: Int,
    @SerializedName("stn_inv_id") val StnInvId: Int,
    @SerializedName("stn_id") val StnId: Int,
    @SerializedName("stn_name") val StnName: String,
    @SerializedName("description") val Description: String,
    @SerializedName("company_id") val CompanyID: Int,
    @SerializedName("status") val Status: Boolean,
    @SerializedName("line_id") val LineId: Int,
    @SerializedName("stn_short_name") val StnShortName: String,
    @SerializedName("stn_national_lang") val StnNationalLang: String,
    @SerializedName("stn_regional_lang") val StnRegionalLang: String,
    @SerializedName("cord_x") val CordX: String,
    @SerializedName("cord_y") val CordY: String,
    @SerializedName("start_date") val StartDate: String,
    @SerializedName("end_date") val EndDate: String?,
    @SerializedName("created_date") val CreatedDate: String,
    @SerializedName("updated_date") val UpdatedDate: String,
    @SerializedName("updated_by") val UpdatedBy: Int
)