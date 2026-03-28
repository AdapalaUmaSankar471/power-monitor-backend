package com.smartpower.power_monitor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int power;

    private boolean status;

    // Monthly budget in Watt-hours (e.g., AC=3000, Fan=500)
    private double monthlyBudget;

    // Track when device was last turned ON
    private LocalDateTime lastTurnedOn;

    // Total usage in minutes this month
    private double totalUsageMinutes;

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public double getMonthlyBudget() { return monthlyBudget; }
    public void setMonthlyBudget(double monthlyBudget) { this.monthlyBudget = monthlyBudget; }

    public LocalDateTime getLastTurnedOn() { return lastTurnedOn; }
    public void setLastTurnedOn(LocalDateTime lastTurnedOn) { this.lastTurnedOn = lastTurnedOn; }

    public double getTotalUsageMinutes() { return totalUsageMinutes; }
    public void setTotalUsageMinutes(double totalUsageMinutes) { this.totalUsageMinutes = totalUsageMinutes; }

    // Actual consumption in Wh = (power * minutes) / 60
    public double getActualConsumption() {
        return (power * totalUsageMinutes) / 60.0;
    }

    // Budget percentage used
    public double getBudgetUsedPercent() {
        if (monthlyBudget <= 0) return 0;
        return (getActualConsumption() / monthlyBudget) * 100;
    }
}