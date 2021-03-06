package br.com.greenlight.ui.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.database.dao.UserDao

class UserRegisterViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun saveRegister(
        email: String,
        password: String,
        nome: String,
        username: String,
        endereco: String,
        codigoPostal: String,
        dataNascimento: String
    ) {
        UserDao
            .saveRegister(email, password)
            .addOnSuccessListener {
                UserDao
                    .saveorUpdateUserProfile(it.user!!.uid, nome, username,
                        endereco, codigoPostal, dataNascimento.toString())
                    .addOnSuccessListener {
                        _status.value = true
                        _msg.value = "Usuário cadastrado com sucesso!"
                    }
                    .addOnFailureListener { failure ->
                        _msg.value = "${failure.message}"
                    }
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }

    fun changeMessage(msg: String) {
        _msg.value = msg
    }
}