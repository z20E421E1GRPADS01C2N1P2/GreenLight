package br.com.greenlight.ui.trip.from

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import br.com.greenlight.database.dao.TripDaoFirestore
import br.com.greenlight.database.dao.VehicleDaoFirestore
import br.com.greenlight.ui.vehicle.form.FormVehicleViewModelFactory
import br.com.greenlight.ui.vehicle.form.VehicleViewModel
import kotlinx.android.synthetic.main.trip_fragment.*

class TripFragment() : Fragment() {

    private lateinit var viewModel: TripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.trip_fragment, container, false)
        val application = requireActivity().application
        val tripViewModelFactory = TripViewModelFactory(TripDaoFirestore(),
            application)
        viewModel = ViewModelProvider(this, tripViewModelFactory)
            .get(TripViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if (status)
                findNavController().popBackStack()
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TripViewModel::class.java)
         btnBuscar.setOnClickListener {
             val nomeViagem = edtTextNomeViagem.text.toString()
             val destino = editTextDestino.text.toString()
             val partida = editTextPartida.text.toString()
             val distancia = editTextDistancia.text.toString()
             viewModel.insertTrip(nomeViagem,partida,destino,distancia)
         }
    }

}