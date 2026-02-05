package com.manish.myapplication.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manish.myapplication.common.database.dao.ConfigDao
import com.manish.myapplication.common.database.dao.FaresDao
import com.manish.myapplication.common.database.dao.UsersDao
import com.manish.myapplication.common.database.dao.PassesDao
import com.manish.myapplication.common.database.dao.VersionDao
import com.manish.myapplication.common.database.dao.AcqParametersDao
import com.manish.myapplication.common.database.dao.CardtypeDao
import com.manish.myapplication.common.database.dao.StationsDao
import com.manish.myapplication.common.database.entity.AcqParameters
import com.manish.myapplication.common.database.entity.Cardtype
import com.manish.myapplication.common.database.entity.Config
import com.manish.myapplication.common.database.entity.Fares
import com.manish.myapplication.common.database.entity.Passes
import com.manish.myapplication.common.database.entity.Stations
import com.manish.myapplication.common.database.entity.Users
import com.manish.myapplication.common.database.entity.Version

@Database(
    entities = [
        Stations::class,
        Passes::class,
        Cardtype::class,
        Config::class,
        Version::class,
        Fares::class,
        Users::class,
        AcqParameters::class
    ],
    version = 1,
    exportSchema = false // Add this to fix the "Schema export" warning
)

abstract class AppDatabase : RoomDatabase(){

    abstract fun stationsDao(): StationsDao
    abstract fun cardtypeDao(): CardtypeDao
    abstract fun configDao(): ConfigDao
    abstract fun versionDao(): VersionDao
    abstract fun usersDao(): UsersDao
    abstract fun passesDao(): PassesDao
    abstract fun faresDao(): FaresDao
    abstract fun acqParametersDao(): AcqParametersDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "ROOM_DATABASE"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .setJournalMode(JournalMode.TRUNCATE)
                .build()
        }
    }

}