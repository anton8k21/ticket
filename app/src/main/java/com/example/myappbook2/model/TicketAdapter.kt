package com.example.myappbook2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myappbook2.databinding.ListItemTicketBinding
import com.example.myappbook2.dto.Ticket


interface OnInteractionListener {
    fun onLike(ticket: Ticket) {}
    fun onShare(ticket: Ticket) {}
}

class CrimeAdapter (
    var ticket: List<Ticket>,
    private val onInteractionListener: OnInteractionListener,
    ) : RecyclerView.Adapter<TicketHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketHolder {
        val binding = ListItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: TicketHolder, position: Int) {
        val ticket = ticket[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int = ticket.size
}

    class TicketHolder(
        private val binding: ListItemTicketBinding,
        private val onInteractionListener: OnInteractionListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ticket: Ticket){
            binding.apply {
                like.isChecked = ticket.likedByMe
                startCityView.text = ticket.startCity
                endCityView.text = ticket.endCity
                startDateView.text = ticket.getDate(ticket.startDate, "dd.MM")
                endDateView.text = ticket.getDate(ticket.endDate, "dd.MM")

                root.setOnClickListener{
                    onInteractionListener.onShare(ticket)
                }
                like.setOnClickListener{
                    onInteractionListener.onLike(ticket)
                }

            }
        }
    }