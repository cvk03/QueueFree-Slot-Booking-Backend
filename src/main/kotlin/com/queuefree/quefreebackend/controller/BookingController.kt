package com.queuefree.quefreebackend.controller

import com.queuefree.quefreebackend.model.CreateBookingRequest
import com.queuefree.quefreebackend.service.BookingService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/machines")
class BookingController(private val bookingService: BookingService) {

    @PostMapping("/{machineUId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    fun createBooking(
        @PathVariable machineUId: String,
        @Valid @RequestBody request: CreateBookingRequest
    ): String {
        bookingService.createBooking(machineUId, request)
        return "Booking successful"
    }
}