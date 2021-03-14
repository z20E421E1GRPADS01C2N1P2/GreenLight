package br.com.greenlight.database.dao
import br.com.greenlight.model.Trip
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TripService {
    // "https://maps.googleapis
    // .com/maps/api/distancematrix/json?origins=Boston,MA|Charlestown,
    //MA&destinations=Lexington,MA|Concord,
// MA&departure_time=now&key=Minha_Chave")
    @GET("maps/api/distancematrix/json?origins={origin}," +
            "&destinations={destination}&departure_time=now&key=Minha_Chave")
    fun obterDistance(@Query("origin") origin:String?, @Query("destination")
    destination:String?):Call<Trip?>?

}