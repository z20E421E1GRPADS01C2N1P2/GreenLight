package br.com.greenlight.api
import br.com.greenlight.model.Trip
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DistanceMatrixApi {
@GET("{origins=}&{destinations=}&departure_time=now&key=Minha_Chave/")
   // "https://maps.googleapis
   // .com/maps/api/distancematrix/json?origins=Boston,MA|Charlestown,
    //MA&destinations=Lexington,MA|Concord,
// MA&departure_time=now&key=Minha_Chave")
fun obterDistance(@Query("origins") origin:String?, @Query("destinations")
destination:String?):Call<Trip?>?


}