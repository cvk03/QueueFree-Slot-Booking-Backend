package com.queuefree.quefreebackend.service

import com.queuefree.quefreebackend.model.CreateUserRequest
import com.queuefree.quefreebackend.model.User
import com.queuefree.quefreebackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository) {

    fun registerUser(uid: String, email: String, request: CreateUserRequest) {

        val existingUser = userRepository.getUser(uid)

        if (existingUser != null) {
            throw RuntimeException("User already exists")
        }

        userRepository.createUser(uid, email, request)
    }

    fun getUser(uid: String): User? {
        return userRepository.getUser(uid)
    }

}