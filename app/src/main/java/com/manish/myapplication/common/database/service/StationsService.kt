package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Stations

class StationsService() {

    private val dao by lazy {
        AppDatabase.instance.stationsDao()
    }
    suspend fun getStations(): List<Stations>{
        return dao.getAll()
    }

    suspend fun getByIdStations(stnid: Int): Stations?{
        return dao.getById(stnid)
    }

}