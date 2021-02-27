package br.com.greenlight.dao

import br.com.greenlight.model.Vehicle
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class VehicleDaoFirestore : VehicleDao {

    private val collection = FirebaseFirestore
        .getInstance()
        .collection("carros")

    override fun insert(vehicle: Vehicle): Task<Void> {
        return collection
            .document(vehicle.placa!!)
            .set(vehicle)
    }
    override fun delete(vehicle: Vehicle): Task<Void> {
        return collection
            .document(vehicle.placa!!)
            .delete()
    }
    override fun get(placa: String): Task<DocumentSnapshot> {
        return collection
            .document(placa)
            .get()
    }

    override fun selectByMarca(marca: String): Task<QuerySnapshot> {
        return collection
            .whereEqualTo("marca", marca)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }
}