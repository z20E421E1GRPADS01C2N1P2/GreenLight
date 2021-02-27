package br.com.greenlight.ui.vehicle.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.dao.VehicleDao

class ListVehicleViewModelFactory  (private val vehicleDao: VehicleDao, private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListVehicleViewModel::class.java))
            return ListVehicleViewModel(vehicleDao, application) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}