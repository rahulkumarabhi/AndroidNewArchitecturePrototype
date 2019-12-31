package com.example.topratedmoviespersistent.dagger

import com.example.topratedmoviespersistent.networking.TMDBApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun getTmdbApi(retrofit: Retrofit): TMDBApi {
        return retrofit.create<TMDBApi>(TMDBApi::class.java)
    }

}