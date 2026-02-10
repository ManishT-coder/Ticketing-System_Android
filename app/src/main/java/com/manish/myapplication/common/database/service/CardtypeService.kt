package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.CardsConfig

object CardtypeService {

    private val dao by lazy {
        AppDatabase.instance.cardtypeDao()
    }

    suspend fun getCardtype(cardTypeId: Int): List<CardsConfig> {
        return dao.getById(cardTypeId)
    }
}