package br.com.greenlight.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

class Trip(
    @DocumentId
    var nomeViagem: String? = null,
    var partida: String? = null,
    var destino:String?  = null,
    var distancia: String? = null,
    var carbonoEmitido: String? = null


)

