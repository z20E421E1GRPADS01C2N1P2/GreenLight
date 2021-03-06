package br.com.greenlight.ui.vehicle.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.database.dao.VehicleDao

class FormVehicleViewModelFactory  (private val vehicleDao: VehicleDao,
private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java))
            return VehicleViewModel(vehicleDao, application) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}