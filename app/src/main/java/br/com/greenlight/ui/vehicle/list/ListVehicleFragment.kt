package br.com.greenlight.ui.vehicle.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.greenlight.R
import br.com.greenlight.adapter.VehicleRecyclerAdapter
import br.com.greenlight.database.dao.VehicleDaoFirestore
import br.com.greenlight.model.Vehicle
import br.com.greenlight.model.`object`.VehicleUtil
import kotlinx.android.synthetic.main.list_vehicle_fragment.*

class ListVehicleFragment : Fragment() {

    private lateinit var viewModel: ListVehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application

        val listVehicles = ListVehicleViewModelFactory(VehicleDaoFirestore())

        viewModel = ViewModelProvider(this, listVehicles)
            .get(ListVehicleViewModel::class.java)

//        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
//            if (status)
//                findNavController().popBackStack()
//        })

        viewModel.vehicle.observe(viewLifecycleOwner){
            setupListViewVehicles(it)
        }

        viewModel.atualizarQuantidade()

        return inflater.inflate(
            R.layout.list_vehicle_fragment,
            container,
            false
        )
    }

    private fun setupListViewVehicles(vehicles: List<Vehicle>) {
        listVehicles.adapter = VehicleRecyclerAdapter(vehicles) {
            VehicleUtil.vehicleSelected = it
        }
        listVehicles.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddVehicle.setOnClickListener {
            findNavController().navigate(R.id.vehicleFragment)
        }
    }

}