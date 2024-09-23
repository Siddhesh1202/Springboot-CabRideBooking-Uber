package com.siddhesh.project.uber.uberApp.services;

public interface EmailSenderService {
    public void sendEmail(String to, String subject, String body);
    void sendEmail(String to[], String subject, String body);
}
