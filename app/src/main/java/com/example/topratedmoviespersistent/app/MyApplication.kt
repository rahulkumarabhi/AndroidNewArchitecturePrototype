package com.example.topratedmoviespersistent.app

import android.app.Application
import com.example.topratedmoviespersistent.dagger.AppComponent
import com.example.topratedmoviespersistent.dagger.AppModule
import com.example.topratedmoviespersistent.dagger.DaggerAppComponent

class MyApplication : Application() {
    companion object{
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = initilizeDagger(this)
    }
    private fun initilizeDagger(app: MyApplication) = DaggerAppComponent.builder()
        .appModule(AppModule(app))
        .build()
    fun provideAppComponent():AppComponent{
        return component
    }
}
