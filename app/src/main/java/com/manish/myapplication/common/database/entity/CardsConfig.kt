package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "CARD_CONFIG"
)
data class CardsConfig(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @SerializedName("card_type_id") val CardTypeId: Int,
    @SerializedName("media_type_id") val MediaTypeId: Int,
    @SerializedName("card_name") val CardName: String,
    @SerializedName("description") val Description: String,
    @SerializedName("card_pro_id") val CardProId: String,
    @SerializedName("card_fee") val CardFee: String,
    @SerializedName("card_sec") val CardSec: String,
    @SerializedName("status") val Staus: Boolean,
    @SerializedName("card_sec_refund_permit") val CardSecRefundPermit: String?,
    @SerializedName("card_sec_refund_charges") val CardSecRefundCharges: String?,
    @SerializedName("ps_type_id") val PstypeId: Int
)
