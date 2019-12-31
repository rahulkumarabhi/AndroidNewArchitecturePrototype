package com.example.topratedmoviespersistent.dagger

import com.example.topratedmoviespersistent.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivityVewModel: MainActivityViewModel)
}