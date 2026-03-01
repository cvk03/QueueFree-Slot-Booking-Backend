package com.queuefree.quefreebackend.service

import com.queuefree.quefreebackend.model.ListBookings
import com.queuefree.quefreebackend.repository.ListBookingsRepository
import org.springframework.stereotype.Service

@Service
class ListBookingsService(private val repository: ListBookingsRepository) {

    fun lisAllBookings() : List<ListBookings>{

        return repository.getAllBookings()
    }

    fun getUpcomingBookings(student_uid: String): List<ListBookings> {
            return repository.getUpcomingBookings(student_uid)
    }

    fun getCompletedBookings(student_uid: String): List<ListBookings> {
        return repository.getCompletedBookings(student_uid)
    }


}