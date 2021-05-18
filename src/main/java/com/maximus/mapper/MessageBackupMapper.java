package com.maximus.mapper;

import com.maximus.dto.MessageBackupDTO;
import com.maximus.model.MessageBackup;

public class MessageBackupMapper {
    public static MessageBackupDTO fromEntityToDTO(MessageBackup entity){
        MessageBackupDTO dto = new MessageBackupDTO();
        dto.setText(entity.getText());
        dto.setTimestamp(entity.getTimestamp());
        return dto;
    }

    public static MessageBackup fromDTOToEntity(MessageBackupDTO dto) {
        MessageBackup entity = new MessageBackup();
        entity.setText(dto.getText());
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }
}
