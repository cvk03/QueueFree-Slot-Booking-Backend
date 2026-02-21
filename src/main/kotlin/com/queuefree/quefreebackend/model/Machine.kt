package com.queuefree.quefreebackend.model

data class Machine(
    val id: String,           // Firestore document ID
    val machineId: String,    // machine_id field
    val hostel: String
)
