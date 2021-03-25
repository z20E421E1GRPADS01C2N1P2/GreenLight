package br.com.greenlight.ui.vehicle.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.database.dao.VehicleDao
import br.com.greenlight.model.Vehicle

class ListVehicleViewModel(
    private val vehicleDao: VehicleDao
) : ViewModel() {

    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicle: LiveData<List<Vehicle>> = _vehicles


    fun atualizarQuantidade() {
        vehicleDao.all() // task<>
            .addSnapshotListener { snapshot, error ->
                if (error != null)
                    Log.i("LstCarroVMSnapshotError", "${error.message}")
                else
                    if (snapshot != null && !snapshot.isEmpty)
                        _vehicles.value = snapshot.toObjects(Vehicle::class.java)
            }
    }
}