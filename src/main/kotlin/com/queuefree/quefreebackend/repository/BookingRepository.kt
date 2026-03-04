package com.queuefree.quefreebackend.repository

import com.google.cloud.Timestamp
import com.google.cloud.firestore.Firestore
import com.queuefree.quefreebackend.model.CreateBookingRequest
import org.springframework.stereotype.Repository
import java.time.Instant
import java.time.ZoneId
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

    fun getAvailableSlotsForDay(
        machineUid: String,
        selectedDayTimestamp: Timestamp
    ): List<Timestamp> {

        val zone = ZoneId.systemDefault()

        // 1 Convert selected day timestamp → LocalDate
        val localDate = Instant.ofEpochSecond(
            selectedDayTimestamp.seconds,
            selectedDayTimestamp.nanos.toLong()
        ).atZone(zone).toLocalDate()

        // 2 Compute start and end of day
        val startOfDayInstant = localDate.atStartOfDay(zone).toInstant()
        val endOfDayInstant = localDate.plusDays(1)
            .atStartOfDay(zone)
            .toInstant()

        val startOfDay = Timestamp.ofTimeSecondsAndNanos(
            startOfDayInstant.epochSecond,
            startOfDayInstant.nano
        )

        val endOfDay = Timestamp.ofTimeSecondsAndNanos(
            endOfDayInstant.epochSecond,
            endOfDayInstant.nano
        )

        // 3 Fetch booked slots using range query
        val snapshot = allMachines
            .document(machineUid)
            .collection("booking")
            .whereGreaterThanOrEqualTo("date", startOfDay)
            .whereLessThan("date", endOfDay)
            .get()
            .get()

        val bookedSlots = snapshot.documents.map {
            it.getTimestamp("date")!!
        }.toSet()

        // 4 Generate all possible slots (7 AM – 10 PM)
        val allSlots = mutableListOf<Timestamp>()

        for (hour in 7 until 22) {  // 7:00 to 21:00 (last slot 21–22)
            val slotInstant = localDate
                .atTime(hour, 0)
                .atZone(zone)
                .toInstant()

            val slotTimestamp = Timestamp.ofTimeSecondsAndNanos(
                slotInstant.epochSecond,
                slotInstant.nano
            )

            allSlots.add(slotTimestamp)
        }

        // 5 Return available slots
        return allSlots.filter { it !in bookedSlots }
    }
}

