package de.miladsa.behpaya.controller;

import de.miladsa.behpaya.Service.DocumentService;
import de.miladsa.behpaya.dto.DocumentDTO;
import de.miladsa.behpaya.model.Document;
import de.miladsa.behpaya.model.DocumentPage;
import de.miladsa.behpaya.model.DocumentSearchCriteria;
import de.miladsa.behpaya.projection.DocumentProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;


@RestController
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public ResponseEntity<Page<DocumentProjection>> getDocuments(DocumentPage documentPage, DocumentSearchCriteria documentSearchCriteria) {
        return new ResponseEntity<>(documentService.getDocuments(documentPage, documentSearchCriteria), HttpStatus.OK);
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<?> getDocumentById(@PathVariable Integer id) {
        var result = documentService.getDocumentById(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document does not exist!");
        } else {
            return ResponseEntity.ok(result);
        }
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

    @DeleteMapping("/document/{id}")
    public ResponseEntity<?> deleteADocument(@PathVariable Integer id) {
        var result = documentService.deleteADocument(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    // TODO: It's not working properly, we need to troubleshoot it.
    @PutMapping(value = "/document/{id}", consumes = "application/json")
    public ResponseEntity<?> updateADocument(@PathVariable Integer id, @RequestBody Document document) {
        try {
            var result = documentService.updateADocument(id, document);
            if (result instanceof String) {
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
