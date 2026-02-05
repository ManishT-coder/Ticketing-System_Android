package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.AcqParameters

class AcqParametersService(context: Context) {

    private val dao = AppDatabase.Companion.getInstance(context).acqParametersDao()

    suspend fun getAcqParameters(): List<AcqParameters>{
        return dao.getAll()
    }

    suspend fun getAcqParametersById(AcqID: Int): AcqParameters?{
        return dao.getById(AcqID )
    }
}