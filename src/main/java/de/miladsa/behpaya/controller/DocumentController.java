package de.miladsa.behpaya.controller;

import de.miladsa.behpaya.Service.DocumentService;
import de.miladsa.behpaya.dto.DocumentDTO;
import de.miladsa.behpaya.projection.DocumentProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public List<DocumentProjection> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @PostMapping(value = "/document")
    public ResponseEntity<?> addADocument(@RequestBody DocumentDTO documentDTO) {
        var violations = documentService.addADocument(documentDTO);
        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(violations);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(documentDTO);
        }
    }
}
