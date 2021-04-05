package br.com.greenlight.ui.user.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import br.com.greenlight.database.dao.UserDao
import br.com.greenlight.model.User
import br.com.greenlight.model.`object`.UserRegistrationUtil
import br.com.greenlight.model.`object`.UserUtil
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.user_profile_fragment.*

class UserProfileFragment : Fragment() {

    private lateinit var profileViewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application
        val userProfileViewModelFactory = UserProfileViewModelFactory(
            UserDao,
            application)

        profileViewModel = ViewModelProvider(this,
            userProfileViewModelFactory).get(UserProfileViewModel::class.java)



        profileViewModel.firebaseUser.observe(viewLifecycleOwner){
            textViewPerfilEmail.text = it?.email
        }

        profileViewModel.status.observe(viewLifecycleOwner) { status ->
            if (status)
                findNavController().popBackStack()
        }

        profileViewModel.msg.observe(viewLifecycleOwner){ msg ->
            if (!msg.isNullOrBlank())
                Snackbar.make(
                    requireContext(),
                    this.requireView(),
                    msg,
                    Snackbar.LENGTH_LONG
                ).show()
        }

        profileViewModel.profilepicture.observe(viewLifecycleOwner) { uri ->
            if (uri != null)
                imageViewProfilePicture.setImageURI(uri)
        }

        profileViewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                textViewPerfilNome.setText(it.nome)
                textViewPerfilUsername.setText(it.username)
                textViewPerfilEndereco.setText(it.endereco)
                textViewPerfilCodigoPostal.setText(it.codigoPostal)
                textViewPerfilDataDeNascimento.setText(it.dataNascimento)
            }
        }

        profileViewModel.userPicture.observe(viewLifecycleOwner) {
            if (it != null){
                imageViewProfilePicture.setImageURI(it)
            }
        }

        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Firebase.auth.currentUser

        if (UserUtil.selectedUser != null) {
            preencherFormulario(UserUtil.selectedUser!!)
        }

        btnEditProfile.setOnClickListener{
            val nome = textViewPerfilNome.text.toString()
            val username = textViewPerfilUsername.text.toString()
            val endereco = textViewPerfilEndereco.text.toString()
            val codigoPostal = textViewPerfilCodigoPostal.text.toString()
            val dataNascimento = textViewPerfilDataDeNascimento.text.toString()

            profileViewModel.store(nome, username, endereco, codigoPostal,
                dataNascimento, uid = user!!.uid)
        }

//        imageViewProfilePicture.setOnClickListener {
//            selecionarImagem()
//        }
    }

//    private fun selecionarImagem() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
//        startActivityForResult(intent, 1)
//    }

    private fun preencherFormulario(user: User) {
        textViewPerfilNome.setText(user.nome)
//        textViewPerfilNome.isEnabled = true

        textViewPerfilUsername.setText(user.username)
//        textViewPerfilUsername.isEnabled = true

        textViewPerfilEndereco.setText(user.endereco)
//        textViewPerfilEndereco.isEnabled = true

        textViewPerfilCodigoPostal.setText(user.codigoPostal)
//        textViewPerfilCodigoPostal.isEnabled = true

        textViewPerfilDataDeNascimento.setText(user.dataNascimento)
//        textViewPerfilDataDeNascimento.isEnabled = true

        profileViewModel.downloadProfilePicture(user.uid!!)

//        btnEditProfile.text = "Atualizar"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            val foto: Uri = data!!.data!!
            val inputStream = requireActivity().contentResolver.openInputStream(foto)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            profileViewModel.setProfilePicture(foto)
        }
    }
}