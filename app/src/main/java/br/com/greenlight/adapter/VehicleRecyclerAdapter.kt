package br.com.greenlight.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.greenlight.R
import br.com.greenlight.model.Vehicle
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.list_vehicle_recycler.view.*

class VehicleRecyclerAdapter(
    private val vehicles: List<Vehicle>,
    private val actionClick: (Vehicle) -> Unit
): RecyclerView.Adapter<VehicleRecyclerAdapter.VehicleViewHolder>() {

    class VehicleViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val textModelo: TextView = itemView.textViewVehicleModelo
        val textMarca: TextView = itemView.textViewVehicleMarca
        val textCombustivel: TextView = itemView.textViewVehicleCombustivel
        val textAno: TextView = itemView.textViewVehicleAno
        val textPlaca: TextView = itemView.textViewVehiclePlaca
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.list_vehicle_recycler,
                parent, false
            )
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehcile = vehicles[position]
        holder.textModelo.text = vehcile.modelo
        holder.textMarca.text = vehcile.marca
        holder.textCombustivel.text = vehcile.tipoCombustivel
        holder.textAno.text = vehcile.ano
        holder.textPlaca.text = vehcile.placa

        holder.itemView.btnDeleteVehicle.setOnClickListener {
            deleteVehicle(vehcile)
        }

        holder.itemView.setOnClickListener {
            actionClick(vehcile)
        }
    }


    override fun getItemCount(): Int = vehicles.size

    private fun deleteVehicle(vehicle: Vehicle): Task<Void> {
        val collection = FirebaseFirestore.getInstance().collection("carros")

        return collection
            .document(vehicle.placa!!)
            .delete()
    }

}