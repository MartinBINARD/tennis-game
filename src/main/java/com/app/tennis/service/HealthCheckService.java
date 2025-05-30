package com.app.tennis.service;

import com.app.tennis.rest.ApplicationStatus;
import com.app.tennis.rest.HealthCheck;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {
    public HealthCheck getApplicationStatus() {
        return new HealthCheck(ApplicationStatus.OK, "Welcome to Tennis Game !");
    }
}