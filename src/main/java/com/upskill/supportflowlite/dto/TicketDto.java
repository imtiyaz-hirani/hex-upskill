package com.upskill.supportflowlite.dto;

import org.springframework.stereotype.Component;

@Component
public class TicketDto {
    private String subject;
    private String message;
    private String priority;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
