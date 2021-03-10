package br.com.greenlight.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

class Trip(
    @DocumentId
    var nomeViagem: String? = null,
    var partida: String? = null,
    var destino:String?  = null,
    var distancia: String? = null,
            //var usuario:User?    = null,
            //var Carro: Vehicle?  = null,
    var vehicle:DocumentReference? = null,


) {

    override fun toString(): String = "$nomeViagem           $distancia"
}

