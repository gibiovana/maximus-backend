package com.maximus.controller;

import java.sql.Timestamp;
import java.util.List;
import com.maximus.dto.MessageBackupDTO;
import com.maximus.model.MessageBackup;
import com.maximus.service.MessageBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maximus.repository.MessageBackupRepository;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/messages")
public class MessageBackupController {

    @Autowired
    private MessageBackupRepository repo;

    private MessageBackupService messageBackupService;

    @Autowired
    public MessageBackupController (MessageBackupService messageBackupService) {
        this.messageBackupService = messageBackupService;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public List<MessageBackup> getAllMessages (){
        return repo.findAll();
    }

    @GetMapping(path = "/deviceName/{value}")
    @ResponseBody
    public MessageBackup getByTimestamp (@PathVariable(required = true, name="value") Timestamp timestamp){
        return repo.findByTimestamp(timestamp);
    }

    @PostMapping("/register")
    public MessageBackupDTO registerMessages(@RequestBody MessageBackupDTO messageBackupDTO) {
        return this.messageBackupService.storeMessageBackupData(messageBackupDTO);
    }
}
