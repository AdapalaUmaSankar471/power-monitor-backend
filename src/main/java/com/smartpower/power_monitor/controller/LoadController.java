// package com.smartpower.power_monitor.controller;

// import com.smartpower.power_monitor.service.LoadService;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// @RequestMapping("/load")
// public class LoadController {

//     private final LoadService loadService;

//     public LoadController(LoadService loadService) {
//         this.loadService = loadService;
//     }

//     @GetMapping("/status")
//     public Map<String, Object> getLoadStatus() {

//         return loadService.calculateLoadStatus();
//     }
// }

package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.Device;
import com.smartpower.power_monitor.repository.DeviceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/load")
@CrossOrigin
public class LoadController {

    private final DeviceRepository deviceRepository;

    public LoadController(DeviceRepository deviceRepository){
        this.deviceRepository=deviceRepository;
    }

    @GetMapping("/status")
    public Map<String,Object> loadStatus(){

        List<Device> devices=deviceRepository.findAll();

        int total=0;

        for(Device d:devices){
            if(d.isStatus()){
                total+=d.getPower();
            }
        }

        String status = total>3000 ? "OVERLOAD" : "NORMAL";

        Map<String,Object> response=new HashMap<>();

        response.put("totalLoad",total);
        response.put("status",status);

        return response;
    }
}