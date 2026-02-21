package com.queuefree.quefreebackend.repository

import com.google.cloud.firestore.Firestore
import org.springframework.stereotype.Repository

@Repository
class BookingRepository(
    private val firestore: Firestore
) {

    fun machineExists(machineId: String): Boolean {

        val doc = firestore.collection("machines")
            .document(machineId)
            .get()
            .get()

        return doc.exists()
    }

    fun bookingExists(machineId: String, date: String): Boolean {

        val snapshot = firestore.collection("machines")
            .document(machineId)
            .collection("Booking")
            .whereEqualTo("date", date)
            .get()
            .get()

        return !snapshot.isEmpty
    }

    fun createBooking(
        machineId: String,
        data: Map<String, Any>) {
        firestore.collection("machines")
            .document(machineId)
            .collection("Booking")
            .add(data)
            .get()
    }
}