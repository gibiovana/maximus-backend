package com.maximus.mapper;

import com.maximus.model.Device;
import com.maximus.dto.DeviceDTO;

public class DeviceMapper {
    public static DeviceDTO fromEntityToDTO(Device entity){
    	DeviceDTO dto = new DeviceDTO();
    	dto.setDeviceId(entity.getDeviceId());
    	dto.setModel(entity.getModel());
    	dto.setOperatingSystem(entity.getOperatingSystem());
    	dto.setOwner(entity.getOwner());
        return dto;
   }
    
    public static Device fromDTOToEntity(DeviceDTO dto) {
    	Device entity = new Device();
    	entity.setDeviceId(dto.getDeviceId());
    	entity.setModel(dto.getModel());
    	entity.setOperatingSystem(dto.getOperatingSystem());
    	entity.setOwner(dto.getOwner());
        return entity;
    }
}
