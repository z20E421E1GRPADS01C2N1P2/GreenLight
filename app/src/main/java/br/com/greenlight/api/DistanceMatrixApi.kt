package br.com.greenlight.api
import br.com.greenlight.model.Trip
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface DistanceMatrixApi {
@GET("{origin}/{destination}json/")
fun obterDistance(@Path("origin") origin:String?, @Path("destination")
destination:String?):Call<Trip?>?


}