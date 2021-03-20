package br.com.greenlight.ui.trip.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.database.dao.TripDao
import br.com.greenlight.database.dao.VehicleDao
import br.com.greenlight.ui.vehicle.list.ListVehicleViewModel

class ListTripViewModelFactory (
    private val tripDao: TripDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListTripViewModel::class.java))
            return ListTripViewModel(tripDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}
