package com.app.tennis.rest;

public record HealthCheck(ApplicationStatus status, String message) {
}
