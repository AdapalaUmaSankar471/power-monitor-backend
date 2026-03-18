package com.smartpower.power_monitor.repository;

import com.smartpower.power_monitor.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long>{
}
