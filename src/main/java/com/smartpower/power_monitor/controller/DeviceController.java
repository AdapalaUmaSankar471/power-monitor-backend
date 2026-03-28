package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.ActivityLog;
import com.smartpower.power_monitor.model.Device;
import com.smartpower.power_monitor.model.PowerLog;
import com.smartpower.power_monitor.repository.ActivityLogRepository;
import com.smartpower.power_monitor.repository.DeviceRepository;
import com.smartpower.power_monitor.repository.PowerLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ActivityLogRepository logRepository;

    @Autowired
    private PowerLogRepository powerLogRepository;

    @GetMapping("/all")
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    @GetMapping("/logs")
    public List<ActivityLog> getLogs() {
        return logRepository.findAll();
    }

    @PostMapping("/add")
    public Device addDevice(@RequestBody Device device) {
        return deviceRepository.save(device);
    }

    // Update monthly budget per device
    @PutMapping("/budget/{id}")
    public Device updateBudget(@PathVariable Long id, @RequestParam double budget) {
        Device device = deviceRepository.findById(id).orElseThrow();
        device.setMonthlyBudget(budget);
        return deviceRepository.save(device);
    }

    @PutMapping("/toggle/{id}")
    public Device toggleDevice(@PathVariable Long id) {

        Device device = deviceRepository.findById(id).orElseThrow();
        boolean wasOn = device.isStatus();

        // If turning OFF — calculate usage time and add to total
        if (wasOn && device.getLastTurnedOn() != null) {
            long minutesOn = ChronoUnit.MINUTES.between(
                device.getLastTurnedOn(), LocalDateTime.now()
            );
            device.setTotalUsageMinutes(device.getTotalUsageMinutes() + minutesOn);
            device.setLastTurnedOn(null);
        }

        // If turning ON — record start time
        if (!wasOn) {
            device.setLastTurnedOn(LocalDateTime.now());
        }

        device.setStatus(!wasOn);
        deviceRepository.save(device);

        // Activity Log
        ActivityLog log = new ActivityLog();
        log.setMessage(device.getName() + (device.isStatus() ? " turned ON" : " turned OFF"));
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);

        // PowerLog — total active load
        List<Device> allDevices = deviceRepository.findAll();
        double totalLoad = allDevices.stream()
                .filter(Device::isStatus)
                .mapToDouble(Device::getPower)
                .sum();

        PowerLog powerLog = new PowerLog();
        powerLog.setTotalLoad(totalLoad);
        powerLog.setTimestamp(LocalDateTime.now());
        powerLogRepository.save(powerLog);

        return device;
    }
}