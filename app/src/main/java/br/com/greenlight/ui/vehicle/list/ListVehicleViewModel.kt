package br.com.greenlight.ui.vehicle.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.greenlight.database.dao.VehicleDao
import br.com.greenlight.model.Vehicle

class ListVehicleViewModel (
    vehicleDao: VehicleDao,
    application: Application
) : AndroidViewModel(application) {

    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicle: LiveData<List<Vehicle>> = _vehicles

    init {
        vehicleDao.all().addSnapshotListener { snapshot, error ->
            if (error == null && snapshot != null && !snapshot.isEmpty)
                _vehicles.value = snapshot.toObjects(Vehicle::class.java)
        }
    }
}