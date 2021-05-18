package com.maximus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="MessageBackup")
public class MessageBackupWrapper {
    @Id
    private Integer messageBackupId;
    private MessageBackup sent;
    private MessageBackup received;

    public MessageBackupWrapper(MessageBackup sent, MessageBackup received) {
        this.sent = sent;
        this.received = received;
    }

    public MessageBackupWrapper() {}

    public void setId(Integer messageBackupId) {
        this.messageBackupId = messageBackupId;
    }

    public Integer getId() {
        return messageBackupId;
    }

    @OneToOne
    public MessageBackup getSent() {
        return sent;
    }

    public void setSent(MessageBackup sent) {
        this.sent = sent;
    }

    @OneToOne
    public MessageBackup getReceived() {
        return received;
    }

    public void setReceived(MessageBackup received) {
        this.received = received;
    }
}
