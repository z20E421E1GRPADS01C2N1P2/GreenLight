package br.com.greenlight.ui.user.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserProfileViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun encerrarSessao() {
        firebaseAuth.signOut()
    }

    val firebaseUser = MutableLiveData<FirebaseUser>()

    init {
        firebaseUser.value = FirebaseAuth.getInstance().currentUser
    }

}