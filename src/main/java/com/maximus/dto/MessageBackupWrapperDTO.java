package com.maximus.dto;

import com.maximus.model.MessageBackup;

import java.io.Serializable;

public class MessageBackupWrapperDTO implements Serializable {

    private MessageBackupDTO sent;
    private MessageBackupDTO received;

    public MessageBackupWrapperDTO() {
    }

    public MessageBackupDTO getSent() {
        return sent;
    }

    public void setSent(MessageBackupDTO sent) {
        this.sent = sent;
    }

    public MessageBackupDTO getReceived() {
        return received;
    }

    public void setReceived(MessageBackupDTO received) {
        this.received = received;
    }



}