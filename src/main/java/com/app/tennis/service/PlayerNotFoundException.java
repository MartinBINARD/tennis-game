package com.app.tennis.service;

public class PlayerNotFoundException extends  RuntimeException {
    public PlayerNotFoundException(String lastName) {
        super("Player with lastname " + lastName + " could not be found");
    }
}
