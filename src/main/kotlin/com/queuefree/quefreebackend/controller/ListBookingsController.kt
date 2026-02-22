package com.queuefree.quefreebackend.controller

import com.queuefree.quefreebackend.model.ListBookings
import com.queuefree.quefreebackend.service.ListBookingsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/allbookings")
class ListBookingsController(private val listBookingsService: ListBookingsService) {

    @GetMapping
    fun getAllBookings(): List<ListBookings> {

        return listBookingsService.lisAllBookings()
    }


}