package com.queuefree.quefreebackend.repository

import com.google.cloud.firestore.Firestore
import com.queuefree.quefreebackend.model.CreateBookingRequest
import com.queuefree.quefreebackend.model.ListBookings
import com.queuefree.quefreebackend.model.Machine
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
class ListBookingsRepository(
    private val firestore: Firestore
) {

    private val collection = firestore.collection("bookings")

    fun getAllBookings(): List<ListBookings>{

        val bookings = collection.get().get()

        return bookings.documents.map { doc ->
            ListBookings(
                bookingId = doc.id,  // document ID
                machine = doc.getString("machine") ?: "",
                student_uid = doc.getString("student_uid") ?: "",
                date = (doc.getTimestamp("date"))!!.toDate(),
                machine_reference = doc.getString("machine_reference") ?: "",
            )

        }

    }

    fun addNewBooking(machineUid: String, request: CreateBookingRequest) {
        val machine = firestore.collection("machines").document(machineUid).get().get()

        val new_bookingsData = mapOf(
            "machine_reference" to machineUid,
            "date" to request.date,
            "machine" to machine.getString("machine_id"),
            "student_uid" to request.student_uid
        )
        firestore.collection("bookings").add(new_bookingsData).get()
    }
}