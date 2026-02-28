package com.queuefree.quefreebackend.controller

import com.queuefree.quefreebackend.model.CreateUserRequest
import com.queuefree.quefreebackend.model.User
import com.queuefree.quefreebackend.service.FirebaseAuthService
import com.queuefree.quefreebackend.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val firebaseAuthService: FirebaseAuthService,
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(
        @RequestHeader("Authorization") authHeader: String,
        @RequestBody request: CreateUserRequest
    ) {

        val token = authHeader.removePrefix("Bearer ")
        val decoded = firebaseAuthService.verifyToken(token)

        userService.registerUser(decoded.uid, decoded.email, request)
    }

    @GetMapping("/me")
    fun getProfile(
        @RequestHeader("Authorization") authHeader: String
    ): User? {

        val token = authHeader.removePrefix("Bearer ")
        val decoded = firebaseAuthService.verifyToken(token)

        return userService.getUser(decoded.uid)
    }
}