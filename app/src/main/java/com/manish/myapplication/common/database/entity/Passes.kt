package com.manish.myapplication.common.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("Passes")
data class Passes(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("pass_inv_id") val PassInvId: Int,
    @SerializedName("media_type_id") val MediaTypeId: Int,
    @SerializedName("product_id") val ProductId: Int,
    @SerializedName("pass_id") val PassId: Int,
    @SerializedName("pass_name") val PassName: String,
    @SerializedName("description") val Description: String,
    @SerializedName("company_id") val CompanyId: Int,
    @SerializedName("status") val Status: Boolean,
    @SerializedName("fare_table_id") val FareTableId: String,
    @SerializedName("start_date") val StartDate: String,
    @SerializedName("end_date") val EndDate: String?,
    @SerializedName("same_stn_over_time_limit") val SameStnOverTimeLimit: String,
    @SerializedName("same_stn_over_time_pen") val SameStnOverTimePen: Int,
    @SerializedName("same_stn_over_time_max_pen") val SameStnOverTimeMaxPen: String,
    @SerializedName("other_stn_over_time_limit") val OtherStnOverTimeLimit: String,
    @SerializedName("other_stn_over_time_pen") val OtherStnOverTimePen: String,
    @SerializedName("other_stn_over_time_max_pen") val OtherStnOverTimeMaxPen: Int,
    @SerializedName("over_travel_pen") val OverTravelPen: String,
    @SerializedName("entry_mismatch_limit") val EntryMismatchLimit: String,
    @SerializedName("entry_mismatch_same_time_pen") val EntryMismatchSameTimePen: String,
    @SerializedName("entry_mismatch_no_exit_pen") val EntryMismatchNoExitPen: String,
    @SerializedName("exit_mismatch_pen") val ExitMismatchPen: Int,
    @SerializedName("entry_exit_control") val EntryExitControl: Boolean,
    @SerializedName("entry_validity_period") val EntryValidityPeriod: String,
    @SerializedName("return_validity_period") val ReturnValidityPeriod: String,
    @SerializedName("pass_validity_period") val PassValidityPeriod: String,
    @SerializedName("grace_period") val GracePeriod: String,
    @SerializedName("trip_count") val TripCount: String,
    @SerializedName("daily_trip_limit") val DailyTripLimit: String?,
    @SerializedName("reload_permit") val ReloadPermit: String?,
    @SerializedName("refund_permit") val RefundPermit: Boolean,
    @SerializedName("refund_charges") val RefundCharges: String,
    @SerializedName("min_sv_topup_amt") val MinSvTopupAmt: String?,
    @SerializedName("sv_step_topup_amt") val SvStepTopupAmt: String?,
    @SerializedName("min_sv_entry_bal") val MinSvEntryBal: String?,
    @SerializedName("max_sv_bal") val MaxSvBal: String?,
    @SerializedName("created_date") val CreateDate: String,
    @SerializedName("updated_date") val UpdatedDate: String,
    @SerializedName("updated_by") val UpdatedBy: Int,
    @SerializedName("card_type_id") val CardTypeId: String?,
    @SerializedName("is_test") val IsTest: Boolean,
    @SerializedName("issue_permit") val IssuePermit: Boolean
)