package com.smartpower.power_monitor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PowerLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalLoad;

    private LocalDateTime timestamp;

    public Long getId() { return id; }

    public double getTotalLoad() { return totalLoad; }

    public void setTotalLoad(double totalLoad) {
        this.totalLoad = totalLoad;
    }

    public LocalDateTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}