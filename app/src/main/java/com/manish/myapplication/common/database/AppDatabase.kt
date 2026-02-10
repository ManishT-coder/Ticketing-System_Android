package com.manish.myapplication.common.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manish.myapplication.common.app.TomApp
import com.manish.myapplication.common.database.dao.ConfigDao
import com.manish.myapplication.common.database.dao.FaresDao
import com.manish.myapplication.common.database.dao.UsersDao
import com.manish.myapplication.common.database.dao.PassesDao
import com.manish.myapplication.common.database.dao.CardtypeDao
import com.manish.myapplication.common.database.dao.StationsDao
import com.manish.myapplication.common.database.entity.CardsConfig
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.database.entity.Fares
import com.manish.myapplication.common.database.entity.Passes
import com.manish.myapplication.common.database.entity.Stations
import com.manish.myapplication.common.database.entity.Users
import com.manish.myapplication.feature.screen.configuration.model.ConfigResponse

@Database(
    entities = [
        Stations::class,
        Passes::class,
        CardsConfig::class,
        Config::class,
        Fares::class,
        Users::class,
        ConfigResponse::class
    ],
    version = 3,
    exportSchema = false // Add this to fix the "Schema export" warning
)

abstract class AppDatabase : RoomDatabase(){

    abstract fun stationsDao(): StationsDao
    abstract fun cardtypeDao(): CardtypeDao
    abstract fun configDao(): ConfigDao
    abstract fun usersDao(): UsersDao
    abstract fun passesDao(): PassesDao
    abstract fun faresDao(): FaresDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "ROOM_DATABASE"

        val instance: AppDatabase by lazy {
            Room.databaseBuilder(
                TomApp.instance,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration(false)
                .build()
        }



    }

}