package br.com.greenlight.api

import br.com.greenlight.database.dao.TripService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DistanceApi {
    private var instance: Retrofit? = null
    fun getInstance(): Retrofit {
        if (instance == null)
            instance = Retrofit
                .Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return instance as Retrofit
    }

    fun getTripService(): TripService {
        return getInstance().create(TripService::class.java)
    }
}