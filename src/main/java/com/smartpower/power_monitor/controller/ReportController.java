package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.PowerLog;
import com.smartpower.power_monitor.repository.PowerLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    // ✅ REAL DATABASE — no more dummy data
    @Autowired
    private PowerLogRepository powerLogRepository;

    // ============================
    // GET ALL LOGS (REAL DATA)
    // ============================
    @GetMapping("/daily")
    public List<PowerLog> getDailyReport() {
        return powerLogRepository.findAll();
    }
}