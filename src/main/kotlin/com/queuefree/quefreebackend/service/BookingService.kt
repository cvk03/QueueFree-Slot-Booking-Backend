package com.queuefree.quefreebackend.service

import com.queuefree.quefreebackend.model.CreateBookingRequest
import com.queuefree.quefreebackend.repository.BookingRepository
import com.queuefree.quefreebackend.repository.ListBookingsRepository
import org.springframework.stereotype.Service

@Service
class BookingService(private val bookingRepository: BookingRepository, private val newBookingRepository: ListBookingsRepository) {

    fun createBooking(machineUId: String, request: CreateBookingRequest){

        val bookingData = mapOf(
            "date" to request.date,
            "student_mis" to request.student_mis,
            "student_name" to request.student_name
        )

        bookingRepository.createBooking(machineUId,request.date,bookingData)

        //also add data to the bookings collection

        newBookingRepository.addNewBooking(machineUId,request)
    }
}