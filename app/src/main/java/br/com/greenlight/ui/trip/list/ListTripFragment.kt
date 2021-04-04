package br.com.greenlight.ui.trip.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.greenlight.R
import br.com.greenlight.adapter.TripRecyclerAdapter
import br.com.greenlight.adapter.VehicleRecyclerAdapter
import br.com.greenlight.database.dao.TripDaoFirestore
import br.com.greenlight.model.Trip
import br.com.greenlight.model.`object`.TripUtil
import br.com.greenlight.model.`object`.VehicleUtil
import kotlinx.android.synthetic.main.list_trip_fragment.*


class ListTripFragment : Fragment() {

    private lateinit var viewModel: ListTripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val listTrips = ListTripViewModelFactory(TripDaoFirestore())

        viewModel = ViewModelProvider(this, listTrips )
            .get(ListTripViewModel::class.java)

        viewModel.trips.observe(viewLifecycleOwner){
            setupListViewTrips(it)
        }

        viewModel.atualizarQuantidade()

        return inflater.inflate(R.layout.list_trip_fragment, container, false)
    }

    private fun setupListViewTrips(trips: List<Trip>) {
        listTrip.adapter = TripRecyclerAdapter(trips) {
            TripUtil.tripSelecinada = it
        }
        listTrip.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabTripForm.alpha = 0.55f

        fabTripForm.setOnClickListener {
            findNavController().navigate(R.id.action_listTripFragment2_to_vehicleTripFragment)
        }

    }

}