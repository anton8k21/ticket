package com.example.myappbook2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappbook2.TicketApi
import com.example.myappbook2.dto.Ticket
import com.example.myappbook2.error.ApiError
import com.example.myappbook2.model.FeedModelState
import kotlinx.coroutines.launch
import java.io.IOException

open class CrimeListViewModel : ViewModel() {

    var data: MutableLiveData<List<Ticket>> = MutableLiveData()
    val dataState = MutableLiveData<FeedModelState>()

    val edited: MutableLiveData<Ticket> by lazy {
        MutableLiveData<Ticket>()
    }

    init {
        loadPosts()
    }

    fun likes(ticket: Ticket){
        ticket.likedByMe = !ticket.likedByMe

    }

    fun loadPosts() = viewModelScope.launch {
        try {
            dataState.value = FeedModelState(loading = true)
            val response = TicketApi.service.getAll()
            if (!response.isSuccessful) {
                dataState.value = FeedModelState(error = true)
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            dataState.value = FeedModelState()
            data.postValue(body.data)

        } catch (e: IOException) {
            dataState.value = FeedModelState(error = true)
        }
    }


}