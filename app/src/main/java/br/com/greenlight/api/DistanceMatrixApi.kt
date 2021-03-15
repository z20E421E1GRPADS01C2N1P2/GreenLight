package br.com.greenlight.api
import br.com.greenlight.model.Trip
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DistanceMatrixApi {

    //departure
    //O valor que vai ser recebido fica entre chaves
@GET("{origins=}&{destinations=}&departure_time=now&key=Minha_Chave/")

/* Exemplo de URI:
"https://maps.googleapis.com/maps/api/distancematrix/json?origins=Boston,MA|Charlestown,
    MA&destinations=Lexington,MA|Concord,
    MA&departure_time=now&key=Minha_Chave")
*/


//Usar Query, em vez de Path
//@Query origins
//@Query destination
fun obterDistance(@Path("origin") origin:String?, @Path("destination")
   // "https://maps.googleapis
   // .com/maps/api/distancematrix/json?origins=Boston,MA|Charlestown,
    //MA&destinations=Lexington,MA|Concord,
// MA&departure_time=now&key=Minha_Chave")
fun obterDistance(@Query("origins") origin:String?, @Query("destinations")
destination:String?):Call<Trip?>?


//Para a API_KEY: criar um arquivo json para configurar essas chaves (?)

}