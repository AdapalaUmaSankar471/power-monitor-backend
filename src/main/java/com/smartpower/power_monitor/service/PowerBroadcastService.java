package com.smartpower.power_monitor.service;

import com.smartpower.power_monitor.model.PowerData;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PowerBroadcastService {

    private final SimpMessagingTemplate messagingTemplate;

    public PowerBroadcastService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastPowerData(double voltage, double current, double power) {

        PowerData data = new PowerData(voltage, current, power);

        messagingTemplate.convertAndSend("/topic/power", data);
    }
}