package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.PowerReading;
import com.smartpower.power_monitor.repository.PowerReadingRepository;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readings")
@CrossOrigin(origins = "*")
public class ReadingController {

    private final PowerReadingRepository powerReadingRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ReadingController(PowerReadingRepository powerReadingRepository,
                             SimpMessagingTemplate messagingTemplate) {

        this.powerReadingRepository = powerReadingRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // Save new power reading
    @PostMapping("/add")
    public PowerReading addReading(@RequestBody PowerReading reading) {

        PowerReading savedReading = powerReadingRepository.save(reading);

        // Send real-time update to dashboard
        messagingTemplate.convertAndSend("/topic/power", savedReading);

        return savedReading;
    }

    // Get all readings
    @GetMapping("/all")
    public List<PowerReading> getAllReadings() {
        return powerReadingRepository.findAll();
    }

    // Get latest reading
    @GetMapping("/latest")
    public PowerReading getLatestReading() {
        return powerReadingRepository.findTopByOrderByIdDesc();
    }

}