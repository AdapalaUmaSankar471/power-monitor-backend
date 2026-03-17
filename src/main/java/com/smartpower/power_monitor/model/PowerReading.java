package com.smartpower.power_monitor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PowerReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double voltage;
    private double current;
    private double power;

    private LocalDateTime timestamp;

    public PowerReading() {}

    public PowerReading(double voltage, double current, double power) {
        this.voltage = voltage;
        this.current = current;
        this.power = power;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public double getVoltage() {
        return voltage;
    }

    public double getCurrent() {
        return current;
    }

    public double getPower() {
        return power;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public void setPower(double power) {
        this.power = power;
    }
}