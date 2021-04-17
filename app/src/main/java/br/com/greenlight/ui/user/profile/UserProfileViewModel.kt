package br.com.greenlight.ui.user.profile

import android.app.Application
import android.net.Uri
import android.provider.ContactsContract
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.database.dao.UserDao
import br.com.greenlight.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class UserProfileViewModel(private val userDao: UserDao, application:
Application) : AndroidViewModel(application) {

    private val firebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser = MutableLiveData<FirebaseUser>()
    val user = MutableLiveData<User>()
    val userPicture = MutableLiveData<Uri>()

    private val _profilepicture = MutableLiveData<Uri?>()
    val profilepicture: LiveData<Uri?> = _profilepicture

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        firebaseUser.value = firebaseAuth.currentUser

        UserDao.selectUserByUid(firebaseUser.value!!.uid)
            .addOnSuccessListener {
                val selectedUser = it.toObject(User::class.java)
                user.value = selectedUser!!
            }
            .addOnFailureListener {
                _msg.value = "Problemas ao persistir os dados."
            }

        val file = File.createTempFile("profilePicture", ".jpeg")
        UserDao.selectUserPictureByUid(firebaseUser.value!!.uid, file)
            .addOnSuccessListener {
                userPicture.value = file.toUri()
            }
    }

    fun store(
        nome: String, username: String, endereco: String, codigoPostal:
        String, dataNascimento: String, uid: String
    ) {
        _status.value = false
//        uploadProfilePicture(uid)
//        val profile = User(nome, username, endereco, codigoPostal, dataNascimento)
        userDao.saveorUpdateUserProfile(uid, nome, username, endereco,
            codigoPostal, dataNascimento)
            .addOnSuccessListener {
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "Problemas ao persistir os dados."
            }
        _status.value = true
    }

    fun setProfilePicture(uri: Uri) {
        _profilepicture.value = uri
    }

    fun downloadProfilePicture(uid: String) {
        val file = File.createTempFile("profile", ".png")
        val fileReference = getFileReference(uid)
        val task = fileReference.getFile(file)
        task
            .addOnSuccessListener {
                _profilepicture.value = file.toUri()
            }
            .addOnFailureListener {
                _msg.value = "Falhou: ${it.message}"
            }
    }

    private fun getFileReference(uid: String): StorageReference {
        // FirebaseStorage
        val firebaseStorage = FirebaseStorage.getInstance()

        // Apontar para a raiz -> referencia
        val storageReference = firebaseStorage.reference

        // Apontar para o arquivo que recebera os dados
        // carros/placa.png
        return storageReference
            .child("users/$uid.png")
    }
}