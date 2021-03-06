package br.com.greenlight.model

import com.google.firebase.firestore.DocumentId

class Trip (var partida: String? = null,
            var destino:String?  = null,
            //var usuario:User?    = null,
            //var Carro: Vehicle?  = null,
            @DocumentId
            var uid:String?      = null) {
}