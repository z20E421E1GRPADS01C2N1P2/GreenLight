package br.com.greenlight.ui.vehicle.form

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.greenlight.R
import br.com.greenlight.database.dao.VehicleDaoFirestore
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.vehicle_fragment.*


class VehicleFragment() : Fragment() {

    private lateinit var viewModel: VehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application
        val formCarroViewModelFactory = FormVehicleViewModelFactory(
            VehicleDaoFirestore(),
            application
        )

        viewModel = ViewModelProvider(this, formCarroViewModelFactory)
            .get(VehicleViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if (status)
                findNavController().popBackStack()
        })

        viewModel.spinnerItems().observe(viewLifecycleOwner, { spinnerData ->
            val spinnerAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                spinnerData
            )
            spinnerOptionCombustivel.adapter = spinnerAdapter
        })

        return inflater.inflate(R.layout.vehicle_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSaveVehicle.setOnClickListener {
            val modelo = edtTextModelo.text.toString()
            val marca = edtTextMarca.text.toString()
            val ano = editTextAno.text.toString()
            val placa = edtTextPlaca.text.toString()
            val combustivel = spinnerOptionCombustivel.selectedItem.toString()

            when {
                modelo.isNullOrBlank() -> Snackbar.make(
                    requireContext(),
                    this.requireView(),
                    "Preencha o campo Modelo corretamente",
                    Snackbar.LENGTH_LONG
                ).show()
                marca.isNullOrBlank() -> Snackbar.make(
                    requireContext(),
                    this.requireView(),
                    "Preencha o campo Marca corretamente",
                    Snackbar.LENGTH_LONG
                ).show()
                ano.isNullOrBlank() -> Snackbar.make(
                    requireContext(),
                    this.requireView(),
                    "Preencha o campo Ano corretamente",
                    Snackbar.LENGTH_LONG
                ).show()
                placa.isNullOrBlank() -> Snackbar.make(
                    requireContext(),
                    this.requireView(),
                    "Preencha o campo Placa corretamente",
                    Snackbar.LENGTH_LONG
                ).show()
                else -> viewModel.insertVehicle(
                    modelo,
                    marca,
                    combustivel,
                    ano,
                    placa
                )
            }
        }

        hideKeyboard()
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}