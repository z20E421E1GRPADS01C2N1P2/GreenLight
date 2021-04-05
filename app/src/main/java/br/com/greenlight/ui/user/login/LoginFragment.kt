package br.com.greenlight.ui.user.login

import android.app.Activity
import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.login_fragment, container, false)

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null)
            findNavController().popBackStack()


//        navView.menu.findItem(R.id.loginFragment).setOnMenuItemClickListener {
//
//            if (firebaseUser != null) {
//                it.isVisible = false
//                navView.menu.findItem(R.id.logoutFragment).isVisible = true
//            } else {
//                it.isVisible = true
//                navView.menu.findItem(R.id.logoutFragment).isVisible = false
//            }
//
//            true
//        }

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            hideKeyboard()
        }

        btnLoginCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_userRegisterFragment)
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}