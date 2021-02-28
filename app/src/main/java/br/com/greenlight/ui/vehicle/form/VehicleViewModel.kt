package br.com.greenlight.ui.vehicle.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.greenlight.dao.VehicleDao
import br.com.greenlight.model.Vehicle
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class VehicleViewModel (
    private val vehicleDao: VehicleDao,
    application: Application
) : AndroidViewModel(application) {

    private val app = application

    private val _spinner = MutableLiveData<List<String>>()
    val spinner: LiveData<List<String>> = _spinner

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    private var combustivel: String? = null

    init {
        _status.value = false
        _msg.value = null
    }

    // Implementando Spinner
    fun spinnerItems(): LiveData<List<String>> {
        _spinner.value = listOf("Selecione um combustível", "Gasolina","Álcool", "Diesel", "Eletricidade")
        return spinner
    }

    fun store(marca: String, modelo: String, placa: String){
        _status.value = false
        val vehicle = Vehicle(marca, modelo, placa)
        vehicleDao.insert(vehicle)
            .addOnSuccessListener {
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "Problemas ao persistir os dados."
            }
        _status.value = true
    }

    private fun getFileReference(placa: String): StorageReference {
        return FirebaseStorage.getInstance().reference.child("carros/$placa")
    }

    fun selecionarCombustivel(placa: String){
        this.combustivel = placa
    }

    fun insertVehicle(
        modelo: String, marca: String, combustivel: String,
        ano: String, placa: String){

        val vehicle = Vehicle(modelo,marca,combustivel,ano, placa)
        vehicleDao.insert(vehicle)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }

    }
}