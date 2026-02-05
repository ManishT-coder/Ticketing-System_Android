package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Version

class VersionService(context: Context) {

    private val dao = AppDatabase.Companion.getInstance(context).versionDao()

    suspend fun getVersion(): List<Version>{
        return dao.getAll()
    }

    suspend fun getVersionById(verId: Int): Version?{
        return dao.getById(verId)
    }

}