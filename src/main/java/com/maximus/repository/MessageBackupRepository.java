package com.maximus.repository;

import com.maximus.model.MessageBackup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MessageBackupRepository extends JpaRepository<MessageBackup, Integer> {

    MessageBackup findByTimestamp(Timestamp timestamp);

    List<MessageBackup> findAll();


}
