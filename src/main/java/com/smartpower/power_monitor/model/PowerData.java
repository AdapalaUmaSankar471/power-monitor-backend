package com.smartpower.power_monitor.model;

public class PowerData {

    private double voltage;
    private double current;
    private double power;

    public PowerData() {}

    public PowerData(double voltage, double current, double power) {
        this.voltage = voltage;
        this.current = current;
        this.power = power;
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