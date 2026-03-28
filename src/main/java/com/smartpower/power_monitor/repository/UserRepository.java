package com.smartpower.power_monitor.repository;

import com.smartpower.power_monitor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}