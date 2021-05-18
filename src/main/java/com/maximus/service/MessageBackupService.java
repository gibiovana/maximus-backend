package com.maximus.service;
import com.maximus.dto.MessageBackupDTO;
import com.maximus.mapper.MessageBackupMapper;
import com.maximus.model.MessageBackup;
import com.maximus.repository.MessageBackupRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MessageBackupService {
    @Autowired
    private MessageBackupRepository repository;

    public MessageBackup getMessageBackup(Timestamp timestamp){
        return this.repository.findByTimestamp(timestamp);
    }

    @Transactional
    public MessageBackupDTO storeMessageBackupData(MessageBackupDTO dto) {
        MessageBackup messageBackup = MessageBackupMapper.fromDTOToEntity(dto);

        messageBackup.setText(dto.getText());
        messageBackup.setTimestamp(dto.getTimestamp());

        messageBackup = this.repository.save(messageBackup);
        return MessageBackupMapper.fromEntityToDTO(messageBackup);
    }
}