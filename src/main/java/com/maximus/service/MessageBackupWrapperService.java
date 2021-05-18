package com.maximus.service;
import com.maximus.dto.MessageBackupWrapperDTO;
import com.maximus.mapper.MessageBackupWrapperMapper;
import com.maximus.model.MessageBackupWrapper;
import com.maximus.repository.MessageBackupWrapperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MessageBackupWrapperService {
    @Autowired
    private MessageBackupWrapperRepository repository;

    public Optional<MessageBackupWrapper> getMessageBackupWrapperById(Integer backupWrapperId){
        return this.repository.findById(backupWrapperId);
    }

    @Transactional
    public MessageBackupWrapperDTO storeMessageBackupWrapperData(MessageBackupWrapperDTO dto) {
        MessageBackupWrapper messageBackup = MessageBackupWrapperMapper.fromDTOToEntity(dto);

        messageBackup.setReceived(dto.getReceived());
        messageBackup.setSent(dto.getSent());

        messageBackup = this.repository.save(messageBackup);
        return MessageBackupWrapperMapper.fromEntityToDTO(messageBackup);
    }
}