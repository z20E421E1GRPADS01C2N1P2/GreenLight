package br.com.greenlight.ui.vehicle.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.greenlight.R

class VehicleFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleFragment()
    }

    private lateinit var viewModel: VehicleViewModel

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