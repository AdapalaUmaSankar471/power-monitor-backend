package com.smartpower.power_monitor.service;

import com.smartpower.power_monitor.model.Device;
import com.smartpower.power_monitor.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository){
        this.deviceRepository=deviceRepository;
    }

    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    public Device saveDevice(Device device){
        return deviceRepository.save(device);
    }

}