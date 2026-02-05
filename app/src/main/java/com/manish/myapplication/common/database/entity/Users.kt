package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("Users")
data class Users(

    @PrimaryKey(autoGenerate = true) val Id: Int,
    @SerializedName("user_inv_id") val UserInvId: Int,
    @SerializedName("user_id") val UserId: Int,
    @SerializedName("first_name") val FirstName: String,
    @SerializedName("middle_name") val MiddleName: String,
    @SerializedName("last_name") val LastName: String,
    @SerializedName("designation") val Designation: String,
    @SerializedName("emp_id") val EmpId: Int,
    @SerializedName("emp_mobile") val EmpMobile: Int,
    @SerializedName("emp_email") val EmpEmail : String,
    @SerializedName("emp_gender") val EmpGender: Int,
    @SerializedName("emp_dob") val EmpDob: String,
    @SerializedName("user_login") val UserLogin: String,
    @SerializedName("user_pwd") val UserPawd: String,
    @SerializedName("status") val Status : Boolean
)