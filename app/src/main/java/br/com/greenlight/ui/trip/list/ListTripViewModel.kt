package br.com.greenlight.ui.trip.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.database.dao.TripDao
import br.com.greenlight.model.Trip


class ListTripViewModel(private val tripDao: TripDao) : ViewModel() {

    private val _trips = MutableLiveData<List<Trip>>()
    val trips: LiveData<List<Trip>> = _trips


    fun atualizarQuantidade() {
        tripDao.all() // task<>
            .addSnapshotListener { snapshot, error ->
                if (error != null)
                    Log.i("LstCarroVMSnapshotError", "${error.message}")
                else
                    if (snapshot != null && !snapshot.isEmpty)
                        _trips.value = snapshot.toObjects(Trip::class.java)
            }
    }
}