package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.PowerLog;
import com.smartpower.power_monitor.repository.PowerLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/power")

public class PowerLogController {

    @Autowired
    private PowerLogRepository repo;

    @GetMapping("/all")
    public List<PowerLog> getLogs(){
        return repo.findAll();
    }
}