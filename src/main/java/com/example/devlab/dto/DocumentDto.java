package com.example.devlab.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
public class DocumentDto {
    private Long id;
    private String name;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime signDate;
    private String accessUser;
}
