package com.queuefree.quefreebackend.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import org.springframework.stereotype.Service

@Service
class FirebaseAuthService {
    fun verifyToken(token: String): FirebaseToken {

        return FirebaseAuth.getInstance().verifyIdToken(token)

    }
}