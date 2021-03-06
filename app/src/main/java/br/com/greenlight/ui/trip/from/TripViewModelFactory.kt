package br.com.greenlight.ui.trip.from

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.database.dao.TripDao

class TripViewModelFactory (private val tripDao: TripDao,
                            private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripViewModel::class.java))
            return TripViewModel(tripDao, application) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}