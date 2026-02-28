package com.queuefree.quefreebackend.model

import java.util.Date

data class User(
    val admin: Boolean?,
    val created_time: Date,
    val display_name: String,
    val email: String,
    val hostel_name: String,
    val mis_number: String,
    val phone_number: String,
    val uid: String
    )
