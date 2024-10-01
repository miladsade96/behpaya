package de.miladsa.behpaya.Service;

import de.miladsa.behpaya.dtos.DocumentProjection;
import de.miladsa.behpaya.model.Document;
import de.miladsa.behpaya.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private DocumentRepository documentRepository;

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentProjection> getAllDocuments() {
        return documentRepository.findAllDocuments();
    }

    public Document addADocument(Document document) {
        return documentRepository.save(document);
    }
}
