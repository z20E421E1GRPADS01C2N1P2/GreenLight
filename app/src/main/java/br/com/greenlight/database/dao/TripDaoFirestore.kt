package br.com.greenlight.database.dao

import br.com.greenlight.model.Trip
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class TripDaoFirestore :TripDao {
    private val collection = FirebaseFirestore
        .getInstance()
        .collection("viagens")

    override fun insert(trip: Trip): Task<Void> {
        return collection
            .document(trip.uid!!)
            .set(trip)
    }
    override fun delete(trip: Trip): Task<Void> {
        return collection
            .document(trip.uid!!)
            .delete()
    }
    override fun get(uid: String): Task<DocumentSnapshot> {
        return collection
            .document(uid)
            .get()
    }

    override fun selectByDestino(destino: String): Task<QuerySnapshot> {
        return collection
            .whereEqualTo("destino", destino)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }
}