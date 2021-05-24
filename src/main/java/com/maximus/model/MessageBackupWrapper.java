package com.maximus.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="MessageBackupWrapper")
public class MessageBackupWrapper implements Serializable {
    @Id
    private Integer messageBackupId;
    @OneToOne
    @JoinColumn
    private MessageBackup sent;

    @OneToOne
    @JoinColumn
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
