package com.smartpower.power_monitor.repository;

import com.smartpower.power_monitor.model.PowerLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerLogRepository extends JpaRepository<PowerLog, Long> {
}