package com.maximus.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maximus.dto.DeviceDTO;
import com.maximus.model.Device;
import com.maximus.repository.DeviceRepository;
import com.maximus.service.DeviceService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/device")
public class DeviceController {
	
	@Autowired
	private DeviceRepository repo;
	
	private DeviceService deviceService;
	
	@Autowired
	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Device> getAllDevices (){
		return repo.findAll();
	}
	
	@GetMapping(path = "/deviceName/{value}")
	@ResponseBody
	public Device getDeviceByModel (@PathVariable(required = true, name="value")String model){
		return repo.findByModel(model);
	}
	
	@PostMapping("/register")
	public DeviceDTO registerDevice(@RequestBody DeviceDTO deviceDTO) {
		return this.deviceService.storeDeviceData(deviceDTO);
	}
}
