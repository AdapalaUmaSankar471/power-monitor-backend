// package com.smartpower.power_monitor.service;

// import com.smartpower.power_monitor.model.Device;
// import com.smartpower.power_monitor.model.PowerReading;
// import com.smartpower.power_monitor.repository.DeviceRepository;
// import com.smartpower.power_monitor.repository.PowerReadingRepository;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @Service
// public class LoadService {

//     private final DeviceRepository deviceRepository;
//     private final PowerReadingRepository powerReadingRepository;

//     private static final double MAX_LOAD = 3000; // 3000 Watts limit

//     public LoadService(DeviceRepository deviceRepository,
//                        PowerReadingRepository powerReadingRepository) {

//         this.deviceRepository = deviceRepository;
//         this.powerReadingRepository = powerReadingRepository;
//     }

//     public Map<String, Object> calculateLoadStatus() {

//         List<Device> devices = deviceRepository.findAll();

//         double totalLoad = 0;

//         for (Device device : devices) {
//             if (device.isStatus()) {
//                 totalLoad += device.getPowerRating();
//             }
//         }

//         // Determine status
//         String status;
//         String message;

//         if (totalLoad > MAX_LOAD) {
//             status = "OVERLOAD";
//             message = "Power usage exceeds safe limit!";
//         } else {
//             status = "NORMAL";
//             message = "Power usage within safe limit.";
//         }

//         // Save power reading in database
//         PowerReading reading = new PowerReading(totalLoad, LocalDateTime.now());

//         powerReadingRepository.save(reading);

//         Map<String, Object> result = new HashMap<>();

//         result.put("totalLoad", totalLoad);
//         result.put("status", status);
//         result.put("message", message);

//         return result;
//     }
// }



package com.smartpower.power_monitor.service;

import com.smartpower.power_monitor.model.Device;
import com.smartpower.power_monitor.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadService {

    private final DeviceRepository deviceRepository;

    public LoadService(DeviceRepository deviceRepository){
        this.deviceRepository=deviceRepository;
    }

    public int calculateLoad(){

        List<Device> devices=deviceRepository.findAll();

        int total=0;

        for(Device d:devices){
            if(d.isStatus()){
                total+=d.getPower();
            }
        }

        return total;
    }
}