package com.maximus.dto;

import java.sql.Timestamp;

public class MessageBackupDTO {
    private String text;
    private Timestamp timestamp;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}