package com.queuefree.quefreebackend.model

import jakarta.validation.constraints.NotBlank

data class CreateBookingRequest(
    @field:NotBlank(message = "Date is required")
    val date: String,

    @field:NotBlank(message = "Student MIS is required")
    val studentMis: String,

    @field:NotBlank(message = "Student name is required")
    val studentName: String
)
