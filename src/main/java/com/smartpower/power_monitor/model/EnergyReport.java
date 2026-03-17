package com.smartpower.power_monitor.model;

public class EnergyReport {

    private double totalPower;
    private double averagePower;

    public EnergyReport() {}

    public EnergyReport(double totalPower, double averagePower) {
        this.totalPower = totalPower;
        this.averagePower = averagePower;
    }

    public double getTotalPower() {
        return totalPower;
    }

    public double getAveragePower() {
        return averagePower;
    }

    public void setTotalPower(double totalPower) {
        this.totalPower = totalPower;
    }

    public void setAveragePower(double averagePower) {
        this.averagePower = averagePower;
    }
}