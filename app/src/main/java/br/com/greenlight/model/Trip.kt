package br.com.greenlight.model

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

class Trip(
    @DocumentId
    var nomeViagem: String? = null,
    var partida: String? = null,
    var destino:String?  = null,
    var distancia: String? = null,
            //var usuario:User?    = null,
            //var Carro: Vehicle?  = null,
    var vehicle: DocumentReference? = null,
    var usuarioLogado: String? = null,
    //var co2Emitido: String? = null


) {

    override fun toString(): String = "$nomeViagem           $distancia"
}

