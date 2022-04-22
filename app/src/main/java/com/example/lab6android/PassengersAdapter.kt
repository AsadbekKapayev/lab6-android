package com.example.lab6android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6android.databinding.ItemPassengerBinding
import com.example.lab6android.model.PartPlace
import com.example.lab6android.model.Passenger
import com.example.lab6android.model.PassengerType

class PassengersAdapter: RecyclerView.Adapter<PassengersAdapter.PassengerViewHolder>() {

    var passengers: List<Passenger> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPassengerBinding.inflate(inflater, parent, false)

        return PassengerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) {
        val passenger = passengers[position]

        with(holder.binding) {
            holder.itemView.tag = passenger
            nameTextView.text = passenger.name
            placeTextView.text = passenger.place
            partPlaceTextView.text = when(passenger.part_place) {
                PartPlace.TOP -> "верхний"
                PartPlace.BOTTOM -> "нижний"
            }
            if (passenger.type == PassengerType.ONLINE) {
                typeTextView.text = passenger.type.toString()
                typeTextView.setBackgroundResource(R.drawable.text_view_shape_green)
            } else if (passenger.type == PassengerType.OFFLINE) {
                typeTextView.text = passenger.type.toString()
                typeTextView.setBackgroundResource(R.drawable.text_view_shape)
            } else {
                typeTextView.text = passenger.type.toString()
                typeTextView.setBackgroundResource(R.drawable.text_view_shape_green)
            }
        }
    }

    override fun getItemCount(): Int {
        return passengers.size
    }

    class PassengerViewHolder(
        val binding: ItemPassengerBinding
    ) : RecyclerView.ViewHolder(binding.root)
}