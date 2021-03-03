package br.com.greenlight.ui.vehicle.show

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.greenlight.R
import br.com.greenlight.model.Vehicle
import kotlinx.android.synthetic.main.show_vehicle_fragment.*
import kotlinx.android.synthetic.main.show_vehicle_fragment.view.*

class ShowVehicleFragment : Fragment() {


    private lateinit var viewModel: ShowVehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ShowVehicleViewModel::class.java)
        viewModel.vehicle.observe(viewLifecycleOwner, {
            preencherInformacaoVehicle(it)
        })

        return inflater.inflate(
            R.layout.show_vehicle_fragment,
            container,
            false
        )
    }

    private fun preencherInformacaoVehicle(vehicle: Vehicle) {
        txtModelo.text = vehicle.modelo
        txtMarca.text = vehicle.marca
        txtCombustivel.text = vehicle.tipoCombustivel
        txtAno.text = vehicle.ano
        txtPlaca.text = vehicle.placa
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(ShowVehicleViewModel::class.java)

    }

}