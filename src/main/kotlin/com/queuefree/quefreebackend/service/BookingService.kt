package com.queuefree.quefreebackend.service

import com.queuefree.quefreebackend.model.CreateBookingRequest
import com.queuefree.quefreebackend.repository.BookingRepository
import com.queuefree.quefreebackend.repository.ListBookingsRepository
import com.queuefree.quefreebackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class BookingService(private val bookingRepository: BookingRepository, private val newBookingRepository: ListBookingsRepository, private val userRepository: UserRepository) {

    fun createBooking(machineUId: String, request: CreateBookingRequest){

        val bookingDate = request.date
        val studentUid = request.student_uid

        val bookingData = mapOf(
            "date" to bookingDate,
            "student_uid" to studentUid,

        )
        val user = userRepository.getUser(studentUid)

        if (user?.admin == true) {
            throw RuntimeException("Admins cannot create bookings")
        }
        bookingRepository.createBooking(machineUId,request.date,bookingData)

        //also add data to the bookings collection

        newBookingRepository.addNewBooking(machineUId,request)
    }
}