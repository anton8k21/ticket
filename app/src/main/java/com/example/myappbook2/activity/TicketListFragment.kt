package com.example.myappbook2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappbook2.databinding.FragmentTicketListBinding
import com.example.myappbook2.dto.Ticket
import com.example.myappbook2.viewModel.CrimeListViewModel
import com.google.android.material.snackbar.Snackbar


class CrimeListFragment: Fragment() {
    val crimeListViewModel: CrimeListViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTicketListBinding.inflate(layoutInflater)

        val crimeRecyclerView =
            binding.root.findViewById(R.id.recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        crimeListViewModel.data.observe(viewLifecycleOwner){
            val adapter = CrimeAdapter(it, object : OnInteractionListener{
                override fun onLike(ticket: Ticket) {
                    crimeListViewModel.likes(ticket)
                }

                override fun onShare(ticket: Ticket) {
                    crimeListViewModel.edited.value = ticket
                    findNavController().navigate(R.id.action_crimeListFragment2_to_cardTicket)
                }
            })
            crimeRecyclerView.adapter = adapter
        }

        crimeListViewModel.dataState.observe(viewLifecycleOwner) { state ->
            binding.progress.isVisible = state.loading
            if (state.error) {
                Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry_loading) { crimeListViewModel.loadPosts() }
                    .show()
            }
        }


        return binding.root
    }


    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }
}

