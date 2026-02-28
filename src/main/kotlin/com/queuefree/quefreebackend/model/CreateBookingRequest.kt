package com.queuefree.quefreebackend.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.Date

data class CreateBookingRequest(
    @field:NotNull(message = "Date is required")
    val date: Date,

    @field:NotBlank(message = "Student MIS is required")
    val student_mis: String,

    @field:NotBlank(message = "Student name is required")
    val student_name: String
)
