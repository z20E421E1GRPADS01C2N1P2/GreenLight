package br.com.greenlight.ui.user.profile

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.database.dao.UserDao
import br.com.greenlight.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.File

class UserProfileViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser = MutableLiveData<FirebaseUser>()
    val user = MutableLiveData<User>()
    val userPicture = MutableLiveData<Uri>()

    init {
        firebaseUser.value = firebaseAuth.currentUser
        UserDao.selectUserByUid(firebaseUser.value!!.uid)
            .addOnSuccessListener {
                val selectedUser = it.toObject(User::class.java)
                user.value = selectedUser!!
            }

        val file = File.createTempFile("profilePicture", ".jpeg")
        UserDao.selectUserPictureByUid(firebaseUser.value!!.uid, file)
            .addOnSuccessListener {
                userPicture.value = file.toUri()
            }
    }

    fun encerrarSessao() {
        firebaseAuth.signOut()
    }
}