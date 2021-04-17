package br.com.greenlight.ui.trip.from

import android.app.Application
import android.icu.text.DecimalFormat
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.greenlight.api.DistanceApi
import br.com.greenlight.database.dao.TripDao
import br.com.greenlight.database.dao.VehicleDaoFirestore
import br.com.greenlight.model.Trip
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

class TripViewModel(
    private val tripDao: TripDao, application: Application,
) :
    AndroidViewModel(application) {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    var destino = listOf<String?>()
    var origem = listOf<String?>()

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    private val _carros = MutableLiveData<MutableList<String>>()
    val carros: LiveData<MutableList<String>> = _carros

    private val _distancia = MutableLiveData<String?>()
    val distancia: MutableLiveData<String?> = _distancia

    private var placa: String? = null

    private val _spinner = MutableLiveData<List<String>>()
    private val spinner: LiveData<List<String>> = _spinner

    init {
        _status.value = false
        _msg.value = null
        _distancia.value = null
        VehicleDaoFirestore()
            .all()
            .get()
            .addOnSuccessListener {
                _carros.value = mutableListOf("Selecione um veículo")
                it.documents.forEach {
                    _carros.value!!.add(it.id)
                }
            }
    }

    fun spinnerItems(): LiveData<List<String>> {
        _spinner.value = listOf("Gasolina", "Álcool", "Diesel")
        return spinner
    }

    fun obterDistancia() {
        viewModelScope.launch {
            try {
                val distanceService = DistanceApi.getTripService()
                if (!destino.isNullOrEmpty() && !origem.isNullOrEmpty()) {
                    Log.i("distance", "entrei no if")
                    val tripDetail =
                        distanceService.obterDistance(origem, destino)
                    Log.i("distance", tripDetail.toString())
                    if (!tripDetail.rows.isNullOrEmpty()) {
                        _distancia.value = tripDetail!!.rows[0].elements[0]
                            .distance.text.toString()
                        Log.i("distance", "${tripDetail.rows[0]}")
                    } else {
                        Log.i("distance", "Rows é nula")
                    }
                    //_distancia.value = tripDetail!!.rows[0].elements[0].distance.value.toString()
                    //_distancia.value = distanceService.obterDistance(origem,destino)
                    //_distancia.value = distanceService.obterDistance(destino,origem)
                    //val trip = distanceService.obterDistance(destino,origem)
                    // _distancia.value = Trip.distacia
                } else {
                    _msg.value = "Não foi possível calcular a distância."
                }
            } catch (e: Exception) {
                _msg.value = e.message
            }
        }
    }

    fun vehicleSelecionadoo(placa: String) {
        this.placa = placa
    }

    fun insertTrip(
        nomeViagem: String,
        partida: String,
        destino: String,
        distancia: String,
        combustivel: String
    ) {
        val usuarioLogado = FirebaseAuth.getInstance().currentUser!!
        // val tokenUsuario = usuarioLogado.getIdToken()

        Log.i("Usuario Corrente ", "$usuarioLogado")

        val vehicle = FirebaseFirestore
            .getInstance()
            .collection("carros")
            .document(placa!!)

        val carbono = carbonoEmitido(combustivel, distancia)

        val trip = Trip(nomeViagem, partida, destino, distancia, carbono)
        tripDao.insert(trip)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }

//    private fun carbonoEmitido(combustivel: String, distancia: String) {
//        val distanciaFinal = distancia.toFloat()
//        var carbonoEmitido: Float = 0.0f
//
//        if (combustivel == "Diesel")
//            carbonoEmitido = distanciaFinal * 280.0f
//        if (combustivel == "Gasolina")
//            carbonoEmitido = distanciaFinal * 217.0f
//        if (combustivel == "Álcool")
//            carbonoEmitido = distanciaFinal * 66.0f
//    }

    private fun carbonoEmitido(combustivel: String, distancia: String): String {
        val distancia = distancia.toFloat()
        var carbonoEmitido: Float = 0.0f

        if (combustivel == "Disel")
            carbonoEmitido = distancia * 280.0f
        if (combustivel == "Gasolina")
            carbonoEmitido = distancia * 217.0f
        if (combustivel == "Álcool")
            carbonoEmitido = distancia * 66.0f

        return carbonoEmitido.toString()
    }
}


