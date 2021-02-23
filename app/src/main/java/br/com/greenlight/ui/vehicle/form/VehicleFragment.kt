package br.com.greenlight.ui.vehicle.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.R
import com.google.firebase.firestore.FirebaseFirestore


class VehicleFragment : Fragment() {

    private lateinit var viewModel: VehicleViewModel

    var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vehicle_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}