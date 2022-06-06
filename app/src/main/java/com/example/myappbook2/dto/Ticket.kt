package com.example.myappbook2.dto

import java.text.SimpleDateFormat
import java.util.*

data class Ticket(
    val startCity:String,
    val startCityCode: String,
    val endCity: String,
    val endCityCode: String,
    var startDate: Date,
    val endDate: Date,
    val price: Int,
    var likedByMe: Boolean = false,
    val searchToken: String
) {
    fun getDate(date: Date, pattern:String): String{
        val dateSimple = SimpleDateFormat(pattern, Locale.getDefault())
        return dateSimple.format(date)

    }
}