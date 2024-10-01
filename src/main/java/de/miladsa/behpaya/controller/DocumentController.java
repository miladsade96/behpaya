package de.miladsa.behpaya.controller;

import de.miladsa.behpaya.Service.DocumentService;
import de.miladsa.behpaya.dtos.DocumentProjection;
import de.miladsa.behpaya.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/document")
    public Document addADocument(@RequestBody Document document) {
        return documentService.addADocument(document);
    }
}
