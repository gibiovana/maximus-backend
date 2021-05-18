package com.maximus.mapper;

import com.maximus.dto.MessageBackupDTO;
import com.maximus.dto.MessageBackupWrapperDTO;
import com.maximus.model.MessageBackup;
import com.maximus.model.MessageBackupWrapper;

public class MessageBackupWrapperMapper {
    public static MessageBackupWrapperDTO fromEntityToDTO(MessageBackupWrapper entity){
        MessageBackupWrapperDTO dto = new MessageBackupWrapperDTO();
        dto.setSent(entity.getSent());
        dto.setReceived(entity.getReceived());
        return dto;
    }

    public static MessageBackupWrapper fromDTOToEntity(MessageBackupWrapperDTO dto) {
        MessageBackupWrapper entity = new MessageBackupWrapper();
        entity.setSent(dto.getSent());
        entity.setReceived(dto.getReceived());
        return entity;
    }
}
