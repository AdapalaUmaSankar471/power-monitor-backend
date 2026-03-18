package com.smartpower.power_monitor.repository;

import com.smartpower.power_monitor.model.PowerReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerReadingRepository extends JpaRepository<PowerReading, Long> {

    PowerReading findTopByOrderByIdDesc();

}