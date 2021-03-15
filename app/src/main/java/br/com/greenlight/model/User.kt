package br.com.greenlight.model

import com.google.firebase.firestore.DocumentId

class User(
    @DocumentId
    var nome: String? = null,
    var username: String? = null,
    var endereco: String? = null,
    var codigoPostal: String? = null,
    var dataNascimento: String? = null,
    val uid: String? = null
)