package com.queuefree.quefreebackend.repository

import com.google.cloud.firestore.Firestore
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
class BookingRepository(
    private val firestore: Firestore
) {

    val allMachines = firestore.collection("machines")

    fun machineExists(machineUId: String): Boolean {

        val machine = allMachines
            .document(machineUId)
            .get()
            .get()

        return machine.exists()
    }

    fun bookingExists(machineUId: String, date: String): Boolean {

        val booking = allMachines
            .document(machineUId)
            .collection("booking")
            .whereEqualTo("date", date)
            .get()
            .get()

        return !booking.isEmpty
    }

    fun createBooking(
        machineUId: String,
        date: Date,
        data: Map<String, Any>
    ): Boolean {

        return firestore.runTransaction { transaction ->

            val machine = allMachines
                .document(machineUId)

            val bookingQuery = machine
                .collection("booking")
                .whereEqualTo("date", date)

            val machineDoc = transaction.get(machine).get()

            if (!machineDoc.exists()) {
                throw Exception("Machine does not exist")
            }

            val bookingSnapshot = transaction.get(bookingQuery).get()

            if (!bookingSnapshot.isEmpty) {
                throw Exception("Slot already booked")
            }

            val newBookingRef = machine.collection("booking").document()

            transaction.set(newBookingRef, data)

            true
        }.get()
    }
}