package com.app.tennis.web;

public record HealthCheck(ApplicationStatus status, String message) {
}
