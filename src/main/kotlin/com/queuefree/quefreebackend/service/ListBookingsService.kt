package com.queuefree.quefreebackend.service

import com.queuefree.quefreebackend.model.ListBookings
import com.queuefree.quefreebackend.repository.ListBookingsRepository
import org.springframework.stereotype.Service

@Service
class ListBookingsService(private val repository: ListBookingsRepository) {

    fun lisAllBookings() : List<ListBookings>{

        return repository.getAllBookings()
    }




}