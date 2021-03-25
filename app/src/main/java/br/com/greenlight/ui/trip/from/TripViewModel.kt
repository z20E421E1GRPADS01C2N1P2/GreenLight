package br.com.greenlight.ui.trip.from

import android.app.Application
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
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch

class TripViewModel(private val tripDao: TripDao, application: Application,
                    ) :
    AndroidViewModel(application) {

    private val app = application
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status
    var destino:String? = null
    var origem:String? =  null

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    private val _carros = MutableLiveData<MutableList<String>>()
    val carros: LiveData<MutableList<String>> = _carros

    private val _distancia = MutableLiveData<String?>()
    val distancia: MutableLiveData<String?> =_distancia

    private var placa: String? = null

    private var uid: String? = null

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
    fun obterDistancia () {
        viewModelScope.launch {
            try {
                val distanceService = DistanceApi.getTripService()
                if (!destino.isNullOrBlank() && !origem.isNullOrBlank()) {
                    Log.i("distance", "entrei no if")
                    val tripDetail =
                        distanceService.obterDistance(origem, destino)
                    Log.i("distance", tripDetail.toString())
                    if(!tripDetail.rows.isNullOrEmpty())
                    {
                        Log.i("distance", "${tripDetail.rows[0]}")
                    }else{
                        Log.i("distance","Rows é nula")
                    }
                    //_distancia.value = tripDetail!!.rows[0].elements[0].distance.value.toString()
                    //_distancia.value = distanceService.obterDistance(origem,destino)
                    //_distancia.value = distanceService.obterDistance(destino,origem)
                    //val trip = distanceService.obterDistance(destino,origem)
                    // _distancia.value = Trip.distacia
                } else {
                    _msg.value = "Não foi possível calcular a distância."
                }
            } catch (e:Exception){
                _msg.value = e.message
            }
        }
    }

    fun store(partida: String, destino: String) {
        _status.value = false
        val trip = Trip(partida, destino)
        tripDao.insert(trip)
            .addOnSuccessListener {
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "Problemas ao persistir os dados."
            }
        _status.value = true
    }

    private fun getFileReference(uid: String): StorageReference {
        return FirebaseStorage.getInstance().reference.child("carros/$uid")
    }

    fun vehicleSelecionadoo(placa:String){
        this.placa = placa
    }

    fun insertTrip(
        nomeViagem: String, partida: String, destino: String, distancia: String
    ) {
        //TODO: Token do Usuario
        val usuarioLogado = FirebaseAuth.getInstance().currentUser!!
       // val tokenUsuario = usuarioLogado.getIdToken()

        Log.i("Usuario Corrente ","$usuarioLogado")

        val vehicle = FirebaseFirestore
            .getInstance()
            .collection("carros")
            .document(placa!!)


        val trip = Trip( nomeViagem,partida, destino, distancia, vehicle)
        tripDao.insert(trip)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }
}


