package br.com.greenlight.database.dao

import br.com.greenlight.model.Trip
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface TripDao {
    fun insert(trip: Trip): Task<Void>

    fun delete(trip: Trip) : Task<Void>

    fun get(uid: String): Task<DocumentSnapshot>

    fun selectByDestino(destino: String): Task<QuerySnapshot>

    fun all(): CollectionReference
}