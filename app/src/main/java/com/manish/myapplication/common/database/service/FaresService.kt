package com.manish.myapplication.common.database.service

import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Fares

class FaresService() {

    private val dao by lazy {
        AppDatabase.instance.faresDao()
    }

    suspend fun getFares(): List<Fares> {
        return dao.getAll()
    }

    suspend fun getFaresById(fareID: Int): Fares? {
        return dao.getById(fareID)
    }

}