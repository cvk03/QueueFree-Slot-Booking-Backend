package com.queuefree.quefreebackend.controller

import com.google.cloud.Timestamp
import com.queuefree.quefreebackend.model.CreateBookingRequest
import com.queuefree.quefreebackend.service.BookingService
import com.queuefree.quefreebackend.service.FirebaseAuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/machines")
class BookingController(
    private val firebaseAuthService: FirebaseAuthService,
    private val bookingService: BookingService) {

    @PostMapping("/{machineUId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    fun createBooking(
        @PathVariable machineUId: String,
        @RequestHeader("Authorization") authHeader: String,
        @Valid @RequestBody request: CreateBookingRequest
    ): String {

        val token = authHeader.removePrefix("Bearer ")
        val decoded = firebaseAuthService.verifyToken(token)

        bookingService.createBooking(machineUId, request)
        return "Booking successful"
    }

    @GetMapping("/{machineUId}/available-slots")
    fun getAvailableSlots(
        @PathVariable machineUId: String,
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam("date") selectedDay: Long
    ): List<Timestamp> {

        val token = authHeader.removePrefix("Bearer ")
        firebaseAuthService.verifyToken(token)

        val dayTimestamp = Timestamp.ofTimeSecondsAndNanos(
            selectedDay / 1000,
            ((selectedDay % 1000) * 1_000_000).toInt()
        )

        return bookingService.getAvailableSlotsForDay(machineUId, dayTimestamp)
    }
}