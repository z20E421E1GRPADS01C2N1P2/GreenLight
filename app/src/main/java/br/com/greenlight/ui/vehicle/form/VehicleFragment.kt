package br.com.greenlight.ui.vehicle.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.greenlight.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vehicle_fragment.*


class VehicleFragment : Fragment() {

    private lateinit var viewModel: VehicleViewModel

    var db = FirebaseFirestore.getInstance()
    lateinit var option: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        option = spinnerOption as Spinner

        val options = arrayOf("Gasolina","Disel","Elétrico")

        option.adapter = ArrayAdapter<String>(context!!, android.R.layout
            .simple_list_item_1,options)

        option.onItemSelectedListener = object : AdapterView
        .OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(activity?.applicationContext, "Escolha um item", Toast
                    .LENGTH_SHORT)
                    .show()
            }
        }

        return inflater.inflate(R.layout.vehicle_fragment, container, false)
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}