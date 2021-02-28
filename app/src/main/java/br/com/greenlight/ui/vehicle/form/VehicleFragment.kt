package br.com.greenlight.ui.vehicle.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import br.com.greenlight.dao.VehicleDaoFirestore
import kotlinx.android.synthetic.main.vehicle_fragment.*


class VehicleFragment() : Fragment() {

    private lateinit var viewModel: VehicleViewModel

//    lateinit var option: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application
        val formCarroViewModelFactory = FormVehicleViewModelFactory(VehicleDaoFirestore(),
            application)

        viewModel = ViewModelProvider(this, formCarroViewModelFactory)
            .get(VehicleViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if (status)
                findNavController().popBackStack()
        })

        viewModel.spinnerItems().observe(viewLifecycleOwner, Observer { spinnerData ->
            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout
                .simple_spinner_item, spinnerData)
            //Desabilitar a primeira opção do spinner
//            spinnerData[0].
            spinnerOptionCombustivel.adapter = spinnerAdapter
        })

//        option = spinnerOptionCombustivel as Spinner

//        val options = arrayOf("Gasolina","Disel","Elétrico")

//        option.adapter = ArrayAdapter<String>(requireContext(), android.R.layout
//            .simple_spinner_dropdown_item,options)
//
//        option.onItemSelectedListener = object : AdapterView
//        .OnItemSelectedListener{
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val combustivel = options[position]
//                viewModel.selecionarCombustivel(combustivel)
//            }

//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Toast.makeText(activity?.applicationContext, "Escolha um item", Toast
//                    .LENGTH_SHORT)
//                    .show()
//            }
//        }

        return inflater.inflate(R.layout.vehicle_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSaveVehicle.setOnClickListener {
            val modelo = edtTextModelo.text.toString()
            val  marca = edtTextMarca.text.toString()
            val ano = editTextAno.text.toString()
            val placa = edtTextPlaca.text.toString()
            val combustivel = spinnerOptionCombustivel.selectedItem.toString()
            viewModel.insertVehicle(modelo, marca, combustivel, ano, placa)
        }
    }

}