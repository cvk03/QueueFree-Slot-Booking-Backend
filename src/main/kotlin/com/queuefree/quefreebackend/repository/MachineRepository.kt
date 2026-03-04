package com.queuefree.quefreebackend.repository
import com.google.cloud.firestore.Firestore
import com.queuefree.quefreebackend.model.CreateBookingRequest
import com.queuefree.quefreebackend.model.Machine
import org.springframework.stereotype.Repository

@Repository
class MachineRepository(
    private val firestore: Firestore
) {

    private val collection = firestore.collection("machines")

    fun getAllMachines(): List<Machine> {
        val snapshot = collection.get().get()

        return snapshot.documents.map { doc ->
            Machine(
                id = doc.id,  // document ID
                machineId = doc.getString("machine_id") ?: "",
                hostel = doc.getString("hostel_name") ?: ""
            )
        }
    }



}
