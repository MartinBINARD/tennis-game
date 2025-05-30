package com.app.tennis.service;

import com.app.tennis.repository.HealthCheckRepository;
import com.app.tennis.ApplicationStatus;
import com.app.tennis.HealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    @Autowired
    private HealthCheckRepository healthCheckRepository;

    public HealthCheck healthcheck() {
        Long applicationConnections = healthCheckRepository.countApplicationConnections();
        if (applicationConnections > 0) {
            return new HealthCheck(ApplicationStatus.OK, "Welcome to Tennis Game!");
        } else {
            return new HealthCheck(ApplicationStatus.KO, "Tennis Game is not fully functional, please check your configuration.");
        }
    }
}