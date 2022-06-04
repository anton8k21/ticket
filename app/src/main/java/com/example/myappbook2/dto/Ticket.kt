package com.example.myappbook2.dto

import java.text.SimpleDateFormat
import java.util.*

data class Ticket(
    val startCity:String,
    val startCityCode: String,
    val endCity: String,
    val endCityCode: String,
    var startDate: String,
    val endDate: String,
    val price: Int,
    var likedByMe: Boolean = false,
    val searchToken: String
) {
    fun getDate(Date: String, pattern:String): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat(pattern, Locale.getDefault())
        val d: Date = sdf.parse(Date)
        val dateOutput = output.format(d).toString()
        return dateOutput


    }
}