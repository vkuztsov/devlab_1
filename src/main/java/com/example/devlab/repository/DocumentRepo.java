package com.example.devlab.repository;

import com.example.devlab.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DocumentRepo extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByAccessUser(String accessUser);

    @Query("SELECT d FROM DocumentEntity d WHERE d.accessUser = ?1 AND d.signDate IS NOT NULL")
    List<DocumentEntity> findSignedDocumentsByAccessUser(String accessUser);

    @Query("SELECT d FROM DocumentEntity d WHERE d.accessUser = ?1 AND d.signDate IS NULL")
    List<DocumentEntity> findNotSignedDocumentsByAccessUser(String accessUser);

    List<DocumentEntity> findByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
