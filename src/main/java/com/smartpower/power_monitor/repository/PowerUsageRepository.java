package com.smartpower.power_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartpower.power_monitor.model.PowerUsage;

public interface PowerUsageRepository extends JpaRepository<PowerUsage, Long> {
}