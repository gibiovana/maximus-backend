package com.maximus.dto;

import com.maximus.model.MessageBackup;

import java.sql.Timestamp;

public class MessageBackupWrapperDTO {
    private MessageBackup sent;
    private MessageBackup received;

    public MessageBackup getSent() {
        return sent;
    }

    public void setSent(MessageBackup sent) {
        this.sent = sent;
    }

    public MessageBackup getReceived() {
        return received;
    }

    public void setReceived(MessageBackup received) {
        this.received = received;
    }
}