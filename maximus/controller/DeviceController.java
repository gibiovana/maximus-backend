package com.example.maximus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.maximus.model.Device;
import com.example.maximus.repository.DeviceRepository;

@Controller
@RequestMapping(path = "/device")
public class DeviceController {
	
	@Autowired
	private DeviceRepository repo;
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Device> getAllDevices (@PathVariable(required = true, name="value")String name){
		return repo.findAll();
	}
	
	@GetMapping(path = "/devicename/{value}")
	@ResponseBody
	public Device getDeviceByModel (@PathVariable(required = true, name="value")String model){
		return repo.findByModel(model);
	}
}
