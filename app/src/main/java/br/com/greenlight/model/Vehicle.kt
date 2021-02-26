package br.com.greenlight.model

import com.google.firebase.firestore.DocumentId

class Vehicle(
    val modelo: String? = null,
    val marca: String? = null,
    val tipoCombustivel: String? = null,
    @DocumentId
    val placa: String? = null,
    val ano: String? = null
) {
}