package br.com.greenlight.dao

import br.com.greenlight.model.Vehicle
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface VehicleDao {

    fun insert(vehicleDao: Vehicle): Task<Void>
    //    fun update(carro: Carro)
    fun delete(vehicleDao: Vehicle) : Task<Void>
    fun get(placa: String): Task<DocumentSnapshot>
    fun selectByMarca(marca: String): Task<QuerySnapshot>
    fun all(): CollectionReference
}