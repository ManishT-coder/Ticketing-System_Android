package com.manish.myapplication.common.database.service

import android.content.Context
import com.manish.myapplication.common.database.AppDatabase
import com.manish.myapplication.common.database.entity.Cardtype

class CardtypeService(context: Context) {

    private val dao = AppDatabase.Companion.getInstance(context).cardtypeDao()

    suspend fun getCardtype(): List<Cardtype>{
        return dao.getAll()
    }

    suspend fun getCardtypeById(cardId: Int): Cardtype?{
        return dao.getById(cardId)
    }
}