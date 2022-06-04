package com.example.myappbook2.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myappbook2.databinding.FragmentTicketBinding
import com.example.myappbook2.dto.Ticket
import com.example.myappbook2.viewModel.CrimeListViewModel
import com.google.android.material.snackbar.Snackbar

class CardTicket: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTicketBinding.inflate(layoutInflater)
        val ticketListViewModel: CrimeListViewModel by activityViewModels()

        ticketListViewModel.edited.value?.let { assigningValues(binding, it)}
        binding.like.setOnClickListener {
            ticketListViewModel.edited.value?.let{
                it.likedByMe = !it.likedByMe
            }
        }
        return binding.root
    }

    fun assigningValues(binding: FragmentTicketBinding, ticket: Ticket){
        binding.startCityView.text = ticket.startCity + " (" + ticket.startCityCode + ")"
        binding.endCityView.text = ticket.endCity + " (" + ticket.endCityCode + ")"
        binding.startDateView.text = ticket.getDate(ticket.startDate,"YYYY-MM-d HH:MM")
        binding.endDateView.text = ticket.getDate(ticket.endDate,"YYYY-MM-d HH:MM")
        binding.priceView.text = ticket.price.toString()
        binding.like.isChecked = ticket.likedByMe
        binding.buyTicket?.setOnClickListener {
            Snackbar.make(binding.layoutButton, "Извините, покупка билета сейчас не доступна!!", Snackbar.ANIMATION_MODE_SLIDE)
                .show()
        }

    }
}