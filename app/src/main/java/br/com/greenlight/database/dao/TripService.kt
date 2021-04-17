package br.com.greenlight.database.dao

import br.com.greenlight.model.TripDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface TripService {
    @GET("maps/api/distancematrix/json?departure_time=now&key=AIzaSyA6-gv6pdSiQyn-6-he4n3HT_J_0vPS6N8")
    suspend fun obterDistance(@Query("origins") origin: List<String?>, @Query("destinations")
    destination: List<String?>): TripDetails
}