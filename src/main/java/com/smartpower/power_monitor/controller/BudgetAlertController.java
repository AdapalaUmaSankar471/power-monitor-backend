package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.Device;
import com.smartpower.power_monitor.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/budget")
public class BudgetAlertController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/status")
    public List<Map<String, Object>> getBudgetStatus() {
        List<Device> devices = deviceRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Device d : devices) {
            if (d.getMonthlyBudget() <= 0) continue;

            double percent = d.getBudgetUsedPercent();
            String alertLevel = "NORMAL";

            if (percent >= 100) alertLevel = "EXCEEDED";
            else if (percent >= 75) alertLevel = "WARNING";

            Map<String, Object> map = new HashMap<>();
            map.put("deviceId", d.getId());
            map.put("deviceName", d.getName());
            map.put("monthlyBudget", d.getMonthlyBudget());
            map.put("actualConsumption", d.getActualConsumption());
            map.put("budgetUsedPercent", Math.round(percent));
            map.put("alertLevel", alertLevel);
            map.put("estimatedMonthEnd", estimateMonthEnd(d));

            result.add(map);
        }

        return result;
    }

    // Estimate total consumption by month end
    private double estimateMonthEnd(Device device) {
        java.time.LocalDate today = java.time.LocalDate.now();
        int dayOfMonth = today.getDayOfMonth();
        int totalDays = today.lengthOfMonth();

        if (dayOfMonth == 0) return 0;

        double dailyAvg = device.getActualConsumption() / dayOfMonth;
        return dailyAvg * totalDays;
    }
}