package br.com.greenlight.ui.user.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val firebaseAuth = FirebaseAuth.getInstance()
//        val firebaseUser = firebaseAuth.currentUser

//        if (firebaseUser == null)
//            findNavController().popBackStack()

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            }
        }

        loginViewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Snackbar.make(
                    requireContext(),
                    this.requireView(),
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
        }

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val firebaseAuth = FirebaseAuth.getInstance()
//        val firebaseUser = firebaseAuth.currentUser

        btnLogin.setOnClickListener {

            val email = editTextLoginEmail.text.toString()
            val password = editTextLoginSenha.text.toString()

            if (email.isNullOrBlank() || password.isNullOrBlank()) {
                Snackbar.make(
                    requireContext(), this.requireView(), "E-mail " +
                            "e/ou senha não confere(m)", Snackbar.LENGTH_LONG
                ).show()
            } else if (email.isNullOrBlank() && password.isNullOrBlank()) {
                Snackbar.make(
                    requireContext(), this.requireView(), "E-mail " +
                            "e/ou senha não confere(m)", Snackbar.LENGTH_LONG
                ).show()
            } else {
                loading.visibility = View.VISIBLE
                loginViewModel.verificarUsuario(email, password)

            }
        }

        btnLoginCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_userRegisterFragment)
        }
    }
}