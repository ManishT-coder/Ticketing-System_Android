//package com.manish.myapplication.feature.screen.configuration.model
//
//import com.google.gson.annotations.SerializedName
//import com.manish.myapplication.common.database.entity.CardsConfig
//import com.manish.myapplication.common.database.entity.Config
//import com.manish.myapplication.common.database.entity.Fares
//import com.manish.myapplication.common.database.entity.Passes
//import com.manish.myapplication.common.database.entity.Stations
//import com.manish.myapplication.common.database.entity.Users
//
//data class ConfigResponse(
//    val status: Boolean,
//    val code: Int,
//    val data: ConfigData?
//) {
//    data class ConfigData(
//
//        @SerializedName("cards")
//        val cardtype: List<CardsConfig>,
//
//        @SerializedName("stations")
//        val stations: List<Stations>,
//
//        @SerializedName("users")
//        val Users: List<Users>,
//
//        @SerializedName("passes")
//        val passes: List<Passes>,
//
//        @SerializedName("fares")
//        val fares: List<Fares>,
//
//        val config: Config?
//    )
//}

package com.manish.myapplication.feature.screen.configuration.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.manish.myapplication.common.database.entity.CardsConfig
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.database.entity.Fares
import com.manish.myapplication.common.database.entity.Passes
import com.manish.myapplication.common.database.entity.Stations
import com.manish.myapplication.common.database.entity.Users

@Entity(tableName = "config_response")
@TypeConverters(ConfigTypeConverter::class)
data class ConfigResponse(

    @PrimaryKey
    val id: Int = 1,   // single row config

    val status: Boolean,
    val code: Int,
    val data: ConfigData?
) {
    data class ConfigData(
        val cardtype: List<CardsConfig>,
        val stations: List<Stations>,
        val users: List<Users>,
        val passes: List<Passes>,
        val fares: List<Fares>,
        val config: Config?
    )
}
