package br.com.greenlight.ui.vehicle.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.database.dao.VehicleDao

class ListVehicleViewModelFactory(
    private val vehicleDao: VehicleDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListVehicleViewModel::class.java))
            return ListVehicleViewModel(vehicleDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}