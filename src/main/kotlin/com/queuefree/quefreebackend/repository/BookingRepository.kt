package com.queuefree.quefreebackend.repository

import com.google.cloud.firestore.Firestore
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
class BookingRepository(
    private val firestore: Firestore
) {

    fun machineExists(machineUId: String): Boolean {

        val doc = firestore.collection("machines")
            .document(machineUId)
            .get()
            .get()

        return doc.exists()
    }

    fun bookingExists(machineUId: String, date: String): Boolean {

        val snapshot = firestore.collection("machines")
            .document(machineUId)
            .collection("Booking")
            .whereEqualTo("date", date)
            .get()
            .get()

        return !snapshot.isEmpty
    }

    fun createBooking(
        machineUId: String,
        date: Date,
        data: Map<String, Any>
    ): Boolean {

        return firestore.runTransaction { transaction ->

            val machineRef = firestore.collection("machines")
                .document(machineUId)

            val bookingQuery = machineRef
                .collection("Booking")
                .whereEqualTo("date", date)

            val machineDoc = transaction.get(machineRef).get()

            if (!machineDoc.exists()) {
                throw Exception("Machine does not exist")
            }

            val bookingSnapshot = transaction.get(bookingQuery).get()

            if (!bookingSnapshot.isEmpty) {
                throw Exception("Slot already booked")
            }

            val newBookingRef = machineRef.collection("Booking").document()

            transaction.set(newBookingRef, data)

            true
        }.get()
    }
}