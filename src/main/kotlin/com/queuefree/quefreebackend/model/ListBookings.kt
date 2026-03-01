package com.queuefree.quefreebackend.model

import java.util.Date

data class ListBookings(
    val bookingId: String, //Firestore doc id for each booking
    val machine_reference: String,           // Firestore machine reference for the booking document ID
    val date : Date,
    val machine : String,
    val student_uid : String,


)
