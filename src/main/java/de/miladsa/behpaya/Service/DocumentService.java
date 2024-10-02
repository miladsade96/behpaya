package de.miladsa.behpaya.Service;

import de.miladsa.behpaya.dtos.DocumentProjection;
import de.miladsa.behpaya.model.Document;
import de.miladsa.behpaya.repository.DocumentRepository;
import de.miladsa.behpaya.validators.DocumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class DocumentService {
    private DocumentRepository documentRepository;
    private DocumentValidator documentValidator;

    @Autowired
    public void setDocumentValidator(DocumentValidator documentValidator) {
        this.documentValidator = documentValidator;
    }

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentProjection> getAllDocuments() {
        return documentRepository.findAllDocuments();
    }

    public Set<String> addADocument(Document document) {
        var violations = documentValidator.validator(document);
        if (!violations.isEmpty()) {
            return violations;
        } else {
            documentRepository.save(document);
            return Collections.emptySet();
        }
    }
}
