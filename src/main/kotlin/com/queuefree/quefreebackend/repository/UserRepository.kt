package com.queuefree.quefreebackend.repository

import com.google.cloud.Timestamp
import com.google.cloud.firestore.Firestore
import com.queuefree.quefreebackend.model.CreateUserRequest
import com.queuefree.quefreebackend.model.User
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
class UserRepository(private val firestore: Firestore) {

    private val collection = firestore.collection("users")

    fun createUser(uid: String, email: String, request: CreateUserRequest) {

        val data = mapOf(
            "display_name" to request.display_name,
            "email" to email,
            "phone_number" to request.phone_number,
            "mis_number" to request.mis_number,
            "hostel_name" to request.hostel_name,
            "admin" to false,
            "created_time" to Timestamp.now(),
        )

        collection.document(uid).set(data)
    }

    fun getUser(uid: String): User? {

        val doc = collection.document(uid).get().get()

        if (!doc.exists()) return null

        return User(
            uid = doc.id,
            display_name = doc.getString("display_name") ?: "",
            email = doc.getString("email") ?: "",
            phone_number = doc.getString("phone_number") ?: "",
            mis_number = doc.getString("mis_number") ?: "",
            hostel_name = doc.getString("hostel_name") ?: "",
            admin = doc.getBoolean("admin") ?: false,
            created_time = (doc.getTimestamp("created_time"))!!.toDate()
        )
    }
}