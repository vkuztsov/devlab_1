package com.example.devlab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "document")
@Getter
@Setter
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime signDate;
    private String accessUser;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
}

