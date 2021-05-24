package com.maximus.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="MessageBackup")
public class MessageBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageBackupId;
    private String text;
    private Timestamp timestamp;

    public MessageBackup() {
    }

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


