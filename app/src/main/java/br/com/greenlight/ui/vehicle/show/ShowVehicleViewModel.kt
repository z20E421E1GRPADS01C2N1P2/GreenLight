package br.com.greenlight.ui.vehicle.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.database.dao.VehicleDaoFirestore
import br.com.greenlight.model.Vehicle

class ShowVehicleViewModel : ViewModel() {

    private val _vehicle = MutableLiveData<Vehicle>()
    val vehicle: LiveData<Vehicle> = _vehicle


    init {
        val vehicle = VehicleDaoFirestore()
            .all()
            .get()
            .addOnSuccessListener {
                val listaVehicle: MutableList<Vehicle> = it.toObjects(Vehicle::class.java)
            }
    }

}