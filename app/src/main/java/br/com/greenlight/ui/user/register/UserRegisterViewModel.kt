package br.com.greenlight.ui.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class UserRegisterViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun saveRegister(email: String, password: String) {
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Usuário autenticado com sucesso!"
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }

    fun changeMessage(msg: String) {
        _msg.value = msg
    }
}