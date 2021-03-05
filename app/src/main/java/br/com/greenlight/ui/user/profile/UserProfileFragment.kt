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

    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        viewModel.firebaseUser.observe(viewLifecycleOwner){
            textViewPerfilEmail.text = it.email
            textViewPerfilUid.text = it.uid
        }

        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPerfilSair.setOnClickListener {
            viewModel.encerrarSessao()
            findNavController().popBackStack()
        }
    }
}