package com.maximus.controller;

import java.util.List;
import java.util.Optional;

import com.maximus.dto.MessageBackupDTO;
import com.maximus.dto.MessageBackupWrapperDTO;
import com.maximus.model.MessageBackupWrapper;
import com.maximus.repository.MessageBackupWrapperRepository;
import com.maximus.service.MessageBackupWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/messagebackup")
public class MessageBackupWrapperController {

    @Autowired
    private MessageBackupWrapperRepository repo;

    private MessageBackupWrapperService messageBackupService;

    @Autowired
    public MessageBackupWrapperController (MessageBackupWrapperService messageBackupService) {
        this.messageBackupService = messageBackupService;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public List<MessageBackupWrapper> getAllMessageBackup (){
        return repo.findAll();
    }

    @GetMapping(path = "/{value}")
    @ResponseBody
    public Optional<MessageBackupWrapper> getByWrapperId (@PathVariable(required = true, name="value") Integer id){
        return repo.findById(id);
    }

    @PostMapping("/register")
    public MessageBackupWrapperDTO registerMessageBackup(@RequestBody MessageBackupWrapperDTO messageBackupDTO) {
        return this.messageBackupService.storeMessageBackupWrapperData(messageBackupDTO);
    }
}
