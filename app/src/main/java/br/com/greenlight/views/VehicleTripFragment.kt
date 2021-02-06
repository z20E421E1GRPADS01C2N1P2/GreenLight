package br.com.greenlight.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.greenlight.R
import br.com.greenlight.viewModels.VehicleTripViewModel

class VehicleTripFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleTripFragment()
    }

    private lateinit var viewModel: VehicleTripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vehicle_trip_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VehicleTripViewModel::class.java)
        // TODO: Use the ViewModel
    }

}