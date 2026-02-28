package com.queuefree.quefreebackend.model

data class CreateUserRequest(
    val display_name: String,
    val phone_number: String,
    val mis_number: String,
    val hostel_name: String
)
