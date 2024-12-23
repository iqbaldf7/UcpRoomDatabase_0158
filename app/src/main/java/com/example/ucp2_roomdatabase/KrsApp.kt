package com.example.ucp2_roomdatabase

import android.app.Application
import com.example.ucp2_roomdatabase.dependenciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) // Membuat instace(pembuat object)
        //Instance adalah object yang dibuat dari class
    }
}