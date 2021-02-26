package br.com.greenlight.ui.vehicle.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import kotlinx.android.synthetic.main.list_vehicle_fragment.*

class ListVehicleFragment : Fragment() {

    private lateinit var viewModel: ListVehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ListVehicleViewModel::class.java)

        viewModel.vehicle.observe(viewLifecycleOwner){
            listViewVehicle.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                it
            )
        }

        return inflater.inflate(
            R.layout.list_vehicle_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddVehicle.setOnClickListener {
            findNavController().navigate(R.id.vehicleFragment)
        }
    }

}