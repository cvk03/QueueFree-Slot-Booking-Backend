package com.queuefree.quefreebackend.service

import com.queuefree.quefreebackend.model.CreateBookingRequest
import com.queuefree.quefreebackend.repository.BookingRepository
import org.springframework.stereotype.Service

@Service
class BookingService(private val bookingRepository: BookingRepository) {

    fun createBooking(machineId: String, request: CreateBookingRequest){
        if(!bookingRepository.machineExists(machineId)){
            throw RuntimeException("Machine not found")
        }

        if(bookingRepository.bookingExists(machineId,request.date))
        {
            throw RuntimeException("Booking already exists for this date")
        }

        val bookingData = mapOf(
            "date" to request.date,
            "studentMis" to request.studentMis,
            "studentName" to request.studentName
        )

        bookingRepository.createBooking(machineId,bookingData)
    }
}