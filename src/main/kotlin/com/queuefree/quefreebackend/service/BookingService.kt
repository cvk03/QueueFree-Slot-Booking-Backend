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
            "studentMis" to request.studentMis,
            "studentName" to request.studentName
        )

        bookingRepository.createBooking(machineUId,request.date,bookingData)

        //also add data to the bookings collection

        newBookingRepository.addNewBooking(machineUId,request)
    }
}