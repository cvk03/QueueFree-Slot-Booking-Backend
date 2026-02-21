package com.queuefree.quefreebackend.service

import com.queuefree.quefreebackend.model.Machine
import com.queuefree.quefreebackend.repository.MachineRepository
import org.springframework.stereotype.Service

@Service
class MachineService (private val machineRepository: MachineRepository) {

    fun getAllMachines(): List<Machine> {
        return machineRepository.getAllMachines()
    }
}