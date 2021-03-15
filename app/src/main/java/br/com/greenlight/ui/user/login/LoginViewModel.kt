package br.com.greenlight.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun verificarUsuario(email: String, password: String) {

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Bem-vindo"
            }
            .addOnFailureListener {
                _msg.value = "Usuário e/ou senha incorreto(s)"
            }




//        if(email.isNullOrBlank() || password.isNullOrBlank()) {
//            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                .addOnFailureListener {
//                    _msg.value = "E-mail e/ou senha não confere(m)"
//                }
//        }
    }
}