package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.PowerUsage;
import com.smartpower.power_monitor.model.Device;
import com.smartpower.power_monitor.model.PowerLog;

import com.smartpower.power_monitor.repository.PowerUsageRepository;
import com.smartpower.power_monitor.repository.DeviceRepository;
import com.smartpower.power_monitor.repository.PowerLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/usage")
@CrossOrigin(origins = "*")
public class PowerUsageController {

    @Autowired
    private PowerUsageRepository powerUsageRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private PowerLogRepository powerLogRepository; // ✅ NEW

    private final SimpMessagingTemplate messagingTemplate;

    public PowerUsageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // ============================
    // ADD USAGE + SAVE LOG + SEND WS
    // ============================
    @PostMapping("/add")
    public PowerUsage addUsage(@RequestBody PowerUsage usage) {

        // 🔹 Get device
        Device device = deviceRepository
                .findById(usage.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found"));

        // 🔹 Calculate energy
        double energy = device.getPower() * usage.getHoursUsed();
        usage.setEnergyConsumed(energy);

        // 🔹 CALCULATE TOTAL SYSTEM LOAD (IMPORTANT FIX)
        List<Device> devices = deviceRepository.findAll();

        double totalLoad = 0;
        for (Device d : devices) {
            if (d.isStatus()) {
                totalLoad += d.getPower();
            }
        }

        usage.setTotalLoad(totalLoad);

        // 🔹 Timestamp
        usage.setTimestamp(LocalDateTime.now());

        // 🔹 Save usage
        PowerUsage savedUsage = powerUsageRepository.save(usage);

        // ============================
        // ✅ SAVE POWER LOG (MAIN PART)
        // ============================
        PowerLog log = new PowerLog();
        log.setTotalLoad(totalLoad);
        log.setTimestamp(LocalDateTime.now());

        powerLogRepository.save(log);

        // ============================
        // ✅ SEND WEBSOCKET UPDATE
        // ============================
        messagingTemplate.convertAndSend("/topic/power", savedUsage);

        return savedUsage;
    }

    // ============================
    // GET ALL USAGE
    // ============================
    @GetMapping("/all")
    public List<PowerUsage> getAllUsage() {
        return powerUsageRepository.findAll();
    }
}