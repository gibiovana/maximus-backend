package com.maximus.mapper;

import com.maximus.dto.MessageBackupDTO;
import com.maximus.dto.MessageBackupWrapperDTO;
import com.maximus.model.MessageBackup;
import com.maximus.model.MessageBackupWrapper;

public class MessageBackupWrapperMapper {
    public static MessageBackupWrapperDTO fromEntityToDTO(MessageBackupWrapper entity){
        MessageBackupWrapperDTO dto = new MessageBackupWrapperDTO();
        MessageBackupDTO sent = MessageBackupMapper.fromEntityToDTO(entity.getSent());
        MessageBackupDTO received = MessageBackupMapper.fromEntityToDTO(entity.getReceived());
        dto.setSent(sent);
        dto.setReceived(received);
        return dto;
    }

    public static MessageBackupWrapper fromDTOToEntity(MessageBackupWrapperDTO dto) {
        MessageBackupWrapper entity = new MessageBackupWrapper();
        MessageBackup sent = MessageBackupMapper.fromDTOToEntity(dto.getSent());
        MessageBackup received = MessageBackupMapper.fromDTOToEntity(dto.getReceived());

        entity.setSent(sent);
        entity.setReceived(received);
        return entity;
    }
}
