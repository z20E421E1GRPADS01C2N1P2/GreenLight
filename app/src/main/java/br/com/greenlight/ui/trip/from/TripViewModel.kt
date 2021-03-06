package br.com.greenlight.ui.trip.from

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.greenlight.database.dao.TripDao
import br.com.greenlight.model.Trip
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class TripViewModel(private val tripDao: TripDao, application: Application) :
    AndroidViewModel(application) {
    private val app = application
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    init {
        _status.value = false
        _msg.value = null
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

    fun insertTrip(
        partida: String, destino: String
    ) {

        val trip = Trip(partida, destino)
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