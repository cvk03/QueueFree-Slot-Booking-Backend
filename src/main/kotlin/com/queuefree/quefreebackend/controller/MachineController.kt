package com.queuefree.quefreebackend.controller

import com.queuefree.quefreebackend.model.Machine
import com.queuefree.quefreebackend.service.FirebaseAuthService
import com.queuefree.quefreebackend.service.MachineService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/machines")
class MachineController (
    private val machineService: MachineService,
    private val firebaseAuthService: FirebaseAuthService
){

    @GetMapping
    fun getAllMachines(
        @RequestHeader("Authorization") authHeader: String
    ): List<Machine> {

        val token = authHeader.removePrefix("Bearer ")
        val user = firebaseAuthService.verifyToken(token)


        return machineService.getAllMachines()
    }
}