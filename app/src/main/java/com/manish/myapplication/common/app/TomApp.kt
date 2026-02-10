package com.manish.myapplication.common.app

import android.app.Application
import androidx.room.Database

class TomApp: Application() {

    companion object{
        lateinit var instance : TomApp
            private set
    }


    override fun onCreate(){
        super.onCreate()
        instance = this

    }

}