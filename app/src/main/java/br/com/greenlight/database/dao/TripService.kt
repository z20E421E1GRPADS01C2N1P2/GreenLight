package br.com.greenlight.database.dao
import br.com.greenlight.model.Trip
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TripService {
    @GET("maps/api/distancematrix/json?origins={origin}," +
            "&destinations={destination}&departure_time=now&key=AIzaSyA6-gv6pdSiQyn-6-he4n3HT_J_0vPS6N8")
   suspend fun obterDistance(@Query("origin") origin:String?, @Query("destination")
    destination:String?):Call<Trip?>?

}