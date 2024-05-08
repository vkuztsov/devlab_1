package com.example.devlab.service;

import com.example.devlab.dto.DocumentDto;
import com.example.devlab.entity.DocumentEntity;
import com.example.devlab.repository.DocumentRepo;
import com.example.devlab.utils.Utility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepo documentRepo;

    public DocumentDto create(DocumentDto documentDto) {
        DocumentEntity document = new DocumentEntity();
        BeanUtils.copyProperties(documentDto, document, "id", "creationDate");

        return converter(documentRepo.save(document));
    }

    public DocumentDto update(DocumentDto documentDto, Long id) {
        DocumentEntity documentEntity = documentRepo.findById(id).orElse(null);
        if(documentEntity != null) {
            BeanUtils.copyProperties(documentDto, documentEntity, Utility.getNullPropertyNames(documentDto));
            documentRepo.save(documentEntity);
        }

        return converter(documentEntity);
    }

    public void delete(Long id) {
        documentRepo.findById(id).ifPresent(documentEntity -> documentRepo.delete(documentEntity));
    }

    public List<DocumentDto> findAllUserDocuments(String username) {
        return listConverter(documentRepo.findByAccessUser(username));
    }

    public List<DocumentDto> findAllSignedUserDocuments(String username, boolean signed) {
        List<DocumentEntity> documentEntities = signed ? documentRepo.findSignedDocumentsByAccessUser(username) :
                documentRepo.findNotSignedDocumentsByAccessUser(username);
        return listConverter(documentEntities);
    }

    public List<DocumentDto> findByCreationDate(LocalDateTime startDate, LocalDateTime endDate) {
        return listConverter(documentRepo.findByCreationDateBetween(startDate, endDate));
    }

    private List<DocumentDto> listConverter(List<DocumentEntity> documentEntities) {
        if(!documentEntities.isEmpty())
            return documentEntities.stream().map(this::converter).collect(Collectors.toList());
        return null;
    }

    private DocumentDto converter(DocumentEntity document) {
        if(document == null)
            return null;

        DocumentDto documentDto = new DocumentDto();
        BeanUtils.copyProperties(document, documentDto);
        return documentDto;
    }
}
