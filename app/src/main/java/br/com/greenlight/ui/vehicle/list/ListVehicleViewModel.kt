package br.com.greenlight.ui.vehicle.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.dao.VehicleDao
import br.com.greenlight.model.Vehicle

class ListVehicleViewModel : ViewModel() {

    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicle: LiveData<List<Vehicle>> = _vehicles

    init {
        VehicleDao.all().addSnapshotListener { snapshot, error ->
            if (error == null && snapshot != null && !snapshot.isEmpty)
                _vehicles.value = snapshot.toObjects(Vehicle::class.java)
        }
    }
}