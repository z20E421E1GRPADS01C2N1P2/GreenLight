package br.com.greenlight.ui.user.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import kotlinx.android.synthetic.main.user_profile_fragment.*

class UserProfileFragment : Fragment() {

    private lateinit var profileViewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this)
            .get(UserProfileViewModel::class.java)

        profileViewModel.firebaseUser.observe(viewLifecycleOwner){
            textViewPerfilEmail.text = it.email
        }

        profileViewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                textViewPerfilNome.text = it.nome
                textViewPerfilUsername.text = it.username
                textViewPerfilEndereco.text = it.endereco
                textViewPerfilCodigoPostal.text = it.codigoPostal
                textViewPerfilDataDeNascimento.text = it.dataNascimento
            }
        }

        profileViewModel.userPicture.observe(viewLifecycleOwner) {
            if (it != null) imageViewProfilePicture.setImageURI(it)
        }

        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPerfilSair.setOnClickListener {
            profileViewModel.encerrarSessao()
            findNavController().popBackStack()
        }
    }
}