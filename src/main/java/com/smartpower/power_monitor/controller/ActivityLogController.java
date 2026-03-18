package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.ActivityLog;
import com.smartpower.power_monitor.repository.ActivityLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@CrossOrigin
public class ActivityLogController {

@Autowired
private ActivityLogRepository repo;

@GetMapping("/all")
public List<ActivityLog> getLogs(){

return repo.findAll();

}

}
