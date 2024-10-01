package de.miladsa.behpaya.repository;

import de.miladsa.behpaya.dtos.DocumentProjection;
import de.miladsa.behpaya.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    @Query("select new de.miladsa.behpaya.dtos.DocumentProjection(d.id,d.title,d.description,d.isHidden) from Document d")
    List<DocumentProjection> findAllDocuments();
}
