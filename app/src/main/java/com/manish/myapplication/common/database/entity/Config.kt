package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("Config")
data class Config(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("activation_time") val Avtime: Long,
    @SerializedName("eq_inv_id") val EqInvId: Int,
    @SerializedName("atek_eq_id") val AtekEqId: String,
    @SerializedName("eq_type_id") val EqTypeId: Int,
    @SerializedName("eq_mode_id") val EqModeId: String?,
    @SerializedName("eq_role_id") val EqRoleId: Int,
    @SerializedName("eq_num") val EqMun: Int,
    @SerializedName("stn_id") val StnId: Int,
    @SerializedName("eq_id") val EqId: String,
    @SerializedName("eq_location_id") val EqLocationId: Int,
    @SerializedName("description") val Description: String,
    @SerializedName("cord_x") val CordX: String,
    @SerializedName("cord_y") val CordY: String,
    @SerializedName("status") val Status: Boolean,
    @SerializedName("start_date") val StartDate: String,
    @SerializedName("end_date") val EndDate: String?,
    @SerializedName("ip_address") val IpAdd: String,
    @SerializedName("primary_ssid") val PrimarySsid: String,
    @SerializedName("primary_ssid_pwd") val PrimarySsidPwd: String,
    @SerializedName("backup_ssid") val BackupSsid: String?,
    @SerializedName("backup_ssid_pwd") val BackupSsidPwd: String?,
    @SerializedName("gateway") val Gateway: String,
    @SerializedName("subnetmask") val Subnetmask: String,
    @SerializedName("is_generated") val IsGenerated: Boolean,
    @SerializedName("is_blacklisted") val IsBlacklisted: Boolean,
    @SerializedName("created_date") val CreatedDate: String,
    @SerializedName("updated_date") val UpdatedDate: String,
    @SerializedName("updated_by") val UpdatedBy: Int,
    @SerializedName("eq_version") val EqVersion: Int,
    @SerializedName("stn_name") val StnName: String?,
    @SerializedName("scs_ip_add") val ScsIpAdd: String,
    @SerializedName("config_version") val ConfigVersion: Int,
    @SerializedName("fare_version") val FareVersion: Int,
    @SerializedName("pass_version") val PassVersion:Int
)