package br.com.greenlight.ui.vehicle.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.dao.VehicleDao
import br.com.greenlight.model.Vehicle

class VehicleViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private var combustivel: String? = null

    fun selecionarCombustivel(placa: String){
        this.combustivel = combustivel
    }

    fun insertVehicle(
        modelo: String, marca: String, combustivel: String,
        ano: String, placa: String){

        val vehicle = Vehicle(modelo,marca,combustivel,ano, placa)
        VehicleDao.insert(vehicle)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }

    }
}