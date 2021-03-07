package br.com.greenlight.model

import br.com.greenlight.api.DistanceMatrixApi
import br.com.greenlight.api.DistanceServiceListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TripService {
    private lateinit var api : DistanceMatrixApi
    private lateinit var listener: DistanceServiceListener

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://maps.googleapis" +
                ".com/maps/api/distancematrix/json?origins=Boston,MA|Charlestown,MA&destinations=Lexington,MA|Concord,MA&departure_time=now&key=AIzaSyA6-gv6pdSiQyn-6-he4n3HT_J_0vPS6N8")
            .addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(DistanceMatrixApi::class.java)

    }
    public fun setDistanceServiceListener(listener: DistanceServiceListener){
        this.listener = listener
    }

   public fun obterDistance(
        origin: String?,
        destination: String?
    ) {
        val call = api.obterDistance(origin, destination)
        call!!.enqueue(object : Callback<Trip?> {
            override fun onResponse(
                call: Call<Trip?>,
                response: Response<Trip?>
            ) {
                if (response.isSuccessful){
                    listener.obterDistanceTerminou(response.body())
                }
            }
            override fun onFailure(call: Call<Trip?>, t: Throwable) {
                listener.falhaReportada(t.message)
            }
        })
    }
}