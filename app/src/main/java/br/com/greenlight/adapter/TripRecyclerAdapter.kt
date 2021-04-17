package br.com.greenlight.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.greenlight.R
import br.com.greenlight.model.Trip
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.list_trip_recycler.view.*

class TripRecyclerAdapter(
    private val trips: List<Trip>,
    private val actionClick: (Trip) -> Unit
): RecyclerView.Adapter<TripRecyclerAdapter.TripViewHolder>() {

    class TripViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val textNomeViagem: TextView = itemView.textViewTripNomeViagem
        val textPartida: TextView = itemView.textViewTripPartida
        val textDestino: TextView = itemView.textViewTripDestino
        val textDistancia: TextView = itemView.textViewTripDistancia
        val txtCarbonoEmitido : TextView = itemView.textViewCarbonoEmitido
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripRecyclerAdapter
    .TripViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.list_trip_recycler,
                parent, false
            )
        return TripRecyclerAdapter.TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripRecyclerAdapter.TripViewHolder, position:
    Int) {
        val trip = trips[position]
        holder.textNomeViagem.text = trip.nomeViagem
        holder.textPartida.text = trip.partida
        holder.textDestino.text = trip.destino
        holder.textDistancia.text = trip.distancia.toString()
        holder.txtCarbonoEmitido.text = trip.carbonoEmitido



        holder.itemView.btnDeleteTrip.setOnClickListener {
            deleteTrip(trip)
        }

        holder.itemView.setOnClickListener {
            actionClick(trip)
        }
    }


    override fun getItemCount(): Int = trips.size

    private fun deleteTrip(trip: Trip): Task<Void> {
        val collection = FirebaseFirestore.getInstance().collection("viagens")

        return collection
            .document(trip.nomeViagem!!)
            .delete()
    }
}