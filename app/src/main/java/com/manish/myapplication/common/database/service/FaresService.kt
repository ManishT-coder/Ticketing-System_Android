package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Fares

class FaresService(context: Context) {

    private val dao = AppDatabase.Companion.getInstance(context).faresDao()

    suspend fun getFares(): List<Fares>{
        return dao.getAll()
    }

    suspend fun getFaresById(fareID: Int): Fares?{
        return dao.getById(fareID)
    }

}