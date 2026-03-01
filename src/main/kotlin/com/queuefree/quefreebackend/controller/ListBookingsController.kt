package com.queuefree.quefreebackend.controller

import com.queuefree.quefreebackend.model.ListBookings
import com.queuefree.quefreebackend.service.FirebaseAuthService
import com.queuefree.quefreebackend.service.ListBookingsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/allbookings")
class ListBookingsController(
    private val listBookingsService: ListBookingsService,
    private val firebaseAuthService: FirebaseAuthService) {

    @GetMapping
    fun getAllBookings(): List<ListBookings> {

        return listBookingsService.lisAllBookings()
    }


    @GetMapping("/upcoming")
    fun getUpcomingBookings(
        @RequestHeader("Authorization") authHeader: String
    ): List<ListBookings> {

        val token = authHeader.removePrefix("Bearer ")
        val user = firebaseAuthService.verifyToken(token)

        return listBookingsService.getUpcomingBookings(user.uid)
    }

    @GetMapping("/completed")
    fun getCompletedBookings(
        @RequestHeader("Authorization") authHeader: String
    ) : List<ListBookings> {

        val token = authHeader.removePrefix("Bearer ")
        val user = firebaseAuthService.verifyToken(token)
        return listBookingsService.getCompletedBookings(user.uid)
    }


}