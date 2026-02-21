package com.queuefree.quefreebackend.controller

import com.queuefree.quefreebackend.model.Machine
import com.queuefree.quefreebackend.service.MachineService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/machines")
class MachineController (private val machineService: MachineService){

    @GetMapping
    fun getAllMachines(): List<Machine> {

        return machineService.getAllMachines()
    }
}