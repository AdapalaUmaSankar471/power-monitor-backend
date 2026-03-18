// package com.smartpower.power_monitor.controller;

// import com.smartpower.power_monitor.model.Device;
// import com.smartpower.power_monitor.repository.DeviceRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/devices")
// public class DeviceController {

//     private final DeviceRepository deviceRepository;

//     public DeviceController(DeviceRepository deviceRepository) {
//         this.deviceRepository = deviceRepository;
//     }


//     @PostMapping("/add")
//     public Device addDevice(@RequestBody Device device) {
//         return deviceRepository.save(device);
//     }

//     @GetMapping("/all")
//     public List<Device> getDevices() {
//         return deviceRepository.findAll();
//     }

//     @PutMapping("/toggle/{id}")
//     public Device toggleDevice(@PathVariable Long id) {

//         Device device = deviceRepository.findById(id).orElseThrow();

//         device.setStatus(!device.isStatus());

//         return deviceRepository.save(device);
//     }
// }

package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.ActivityLog;
import com.smartpower.power_monitor.model.Device;
import com.smartpower.power_monitor.repository.ActivityLogRepository;
import com.smartpower.power_monitor.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/devices")

public class DeviceController {

@Autowired
private DeviceRepository deviceRepository;

@Autowired
private ActivityLogRepository logRepository;

@GetMapping("/all")
public List<Device> getDevices(){
return deviceRepository.findAll();
}

@GetMapping("/logs")
public List<ActivityLog> getLogs(){
    return logRepository.findAll();
}

@PostMapping("/add")
public Device addDevice(@RequestBody Device device){
return deviceRepository.save(device);
}

@PutMapping("/toggle/{id}")
public Device toggleDevice(@PathVariable Long id){

Device device = deviceRepository.findById(id).orElseThrow();

device.setStatus(!device.isStatus());

deviceRepository.save(device);

ActivityLog log = new ActivityLog();

log.setMessage(device.getName() + (device.isStatus() ? " turned ON" : " turned OFF"));

log.setTimestamp(java.time.LocalDateTime.now());

logRepository.save(log);

return device;

}

}