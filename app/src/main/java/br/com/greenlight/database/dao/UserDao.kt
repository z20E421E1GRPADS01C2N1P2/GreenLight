package br.com.greenlight.database.dao

import br.com.greenlight.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.File

object UserDao {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val collection = FirebaseFirestore.getInstance().collection("users")

    private val storageReference = FirebaseStorage
        .getInstance()
        .reference
        .child("users")

    fun saveRegister(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    //Get other user register fields from user by their uid
    fun saveorUpdateUserProfile(
        uid: String,
        nome: String,
        username: String,
        endereco: String,
        codigoPostal: String,
        dataNascimento: String
    ): Task<Void> {
        return collection
            .document(uid)
            .set(User(nome, username, endereco, codigoPostal, dataNascimento))
    }

    fun saveUserImageProfile(uid: String, image: ByteArray): UploadTask {
        return storageReference
            .child("${uid}.jpeg")
            .putBytes(image)
    }

    //Search for a user by their uid
    fun selectUserByUid(uid: String): Task<DocumentSnapshot> {
        return collection.document(uid).get()
    }

    fun selectUserPictureByUid(uid: String, file: File): FileDownloadTask {
        return storageReference.child("${uid}.jpeg").getFile(file)
    }
}