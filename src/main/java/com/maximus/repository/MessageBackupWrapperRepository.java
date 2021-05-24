package com.maximus.repository;

import com.maximus.model.MessageBackupWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageBackupWrapperRepository extends JpaRepository<MessageBackupWrapper, Integer> {

    Optional<MessageBackupWrapper> findById(Integer messageBackupId);

    List<MessageBackupWrapper> findAll();

}
