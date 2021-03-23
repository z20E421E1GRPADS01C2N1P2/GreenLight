package br.com.greenlight.model.`object`

object UserRegistrationUtil {

    private val existingUsernames = listOf(
        "Fulano", "Ciclano")

    fun validateRegistrationUtil(
        nome: String,
        username: String,
        endereco: String,
        codigoPostal: String,
        dataNascimento: String
    ) : Boolean {
        return true
    }
}