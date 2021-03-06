package br.com.greenlight.ui.user.register

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.greenlight.database.dao.UserDao
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class UserRegisterViewModel : ViewModel() {

    private var _profileImage: Bitmap? = null

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
                if (it.user != null)
                    saveProfileInfos(
                        it.user!!.uid,
                        nome,
                        username,
                        endereco,
                        codigoPostal,
                        dataNascimento
                    )
            }

            .addOnFailureListener {
                changeMessage("${it.message}")
            }
    }

    private fun saveProfileInfos(
        userId: String,
        nome: String,
        username: String,
        endereco: String,
        codigoPostal: String,
        dataNascimento: String
    ) {
        UserDao
            .saveorUpdateUserProfile(
                userId, nome, username, //Firestore
                endereco, codigoPostal, dataNascimento
            )

            .addOnSuccessListener {
                saveProfilePicture(userId)
            }

            .addOnFailureListener {
                changeMessage("${it.message}")
            }
    }

    private fun saveProfilePicture(userId: String) {
        UserDao.saveUserImageProfile(
            userId, convertBitmapToByteArray(_profileImage!!))
            .addOnSuccessListener {
                _status.value = true
                changeMessage("Usuário cadastrado com sucesso!")
            }
            .addOnFailureListener {
                changeMessage("${it.message}")
            }
    }

    fun takePicture(image: Bitmap) {
        _profileImage = image
    }

    fun changeMessage(msg: String) {
        _msg.value = msg
    }

    private fun convertBitmapToByteArray(image: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

        return outputStream.toByteArray()
    }
}