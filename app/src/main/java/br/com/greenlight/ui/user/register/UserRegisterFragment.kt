package br.com.greenlight.ui.user.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.R

class UserRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = UserRegisterFragment()
    }

    private lateinit var viewModel: UserRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.user_register_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(UserRegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}