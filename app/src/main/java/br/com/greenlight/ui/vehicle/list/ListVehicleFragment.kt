package br.com.greenlight.ui.vehicle.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.greenlight.R

class ListVehicleFragment : Fragment() {

    companion object {
        fun newInstance() = ListVehicleFragment()
    }

    private lateinit var viewModel: ListVehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.list_vehicle_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(ListVehicleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}