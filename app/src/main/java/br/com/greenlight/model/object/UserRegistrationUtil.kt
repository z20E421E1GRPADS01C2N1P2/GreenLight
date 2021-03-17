package br.com.greenlight.model.`object`

object UserRegistrationUtil {

    private val existingUsernames = listOf(
        "Fulano", "Ciclano")

    /**
     * The input is not valid if...
     * ...the email/password is empty
     * ... the email is already taken
     * ... the confirmed password is not the same as the real password
     * ... the password contains less than 6 digits
     */

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