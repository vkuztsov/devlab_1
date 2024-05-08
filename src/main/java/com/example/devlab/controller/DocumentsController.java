package com.example.devlab.controller;

import com.example.devlab.dto.DocumentDto;
import com.example.devlab.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentsController {
    @Autowired
    private DocumentService documentService;

    @PostMapping
    private DocumentDto create(@RequestBody DocumentDto documentDto) {
        return documentService.create(documentDto);
    }

    @PutMapping
    private DocumentDto update(@RequestBody DocumentDto documentDto, @RequestParam Long id) {
        return documentService.update(documentDto, id);
    }

    @DeleteMapping
    private void delete(@RequestParam Long id) {
        documentService.delete(id);
    }

    @GetMapping("/by-user")
    private List<DocumentDto> getUserDocuments(@RequestParam String user) {
        return documentService.findAllUserDocuments(user);
    }

    @GetMapping("/by-user-sign")
    private List<DocumentDto> getSignedUserDocuments(@RequestParam String user, @RequestParam boolean signed) {
        return documentService.findAllSignedUserDocuments(user, signed);
    }

    @GetMapping("/by-date")
    private List<DocumentDto> getDocumentsByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return documentService.findByCreationDate(startDate, endDate);
    }
}
