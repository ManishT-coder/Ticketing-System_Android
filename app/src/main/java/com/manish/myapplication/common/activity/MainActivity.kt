package com.manish.myapplication.common.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import com.manish.myapplication.common.app.TomApp
import com.manish.myapplication.common.app.TomApp.Companion.instance
import com.manish.myapplication.common.theme.MyApplicationTheme
import com.manish.myapplication.feature.screen.configuration.ConfigurationScreen

class MainActivity : ComponentActivity() {

    companion object{
        lateinit var instance : MainActivity
            private set
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        instance = this

        setContent {
            MyApplicationTheme {
                Navigator(ConfigurationScreen)
            }
        }
    }
}
