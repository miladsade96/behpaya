package de.miladsa.behpaya.repository;

import de.miladsa.behpaya.model.Document;
import de.miladsa.behpaya.model.DocumentPage;
import de.miladsa.behpaya.model.DocumentSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class DocumentCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public DocumentCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Document> findAllWithFilters(DocumentPage documentPage, DocumentSearchCriteria documentSearchCriteria) {
        CriteriaQuery<Document> criteriaQuery = criteriaBuilder.createQuery(Document.class);
        Root<Document> documentRoot = criteriaQuery.from(Document.class);
        Predicate predicate = getPredicate(documentSearchCriteria, documentRoot);
        criteriaQuery.where(predicate);
        setOrder(documentPage, criteriaQuery, documentRoot);

        TypedQuery<Document> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(documentPage.getPageNumber() * documentPage.getPageSize());
        typedQuery.setMaxResults(documentPage.getPageSize());

        Pageable pageable = getPageable(documentPage);
        long documentsCount = getDocumentsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, documentsCount);
    }

    // It's not working properly!!!!!!
    private Predicate getPredicate(DocumentSearchCriteria documentSearchCriteria, Root<Document> documentRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(documentSearchCriteria.getTitle())) {
            predicates.add(
                    criteriaBuilder.like(documentRoot.get("title"), "%" + documentSearchCriteria.getTitle() + "%")
            );
        }
        if (Objects.nonNull(documentSearchCriteria.getDescription())) {
            predicates.add(
                    criteriaBuilder.like(documentRoot.get("description"), "%" + documentSearchCriteria.getDescription() + "%")
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(DocumentPage documentPage, CriteriaQuery<Document> criteriaQuery, Root<Document> documentRoot) {
        if (documentPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(documentRoot.get(documentPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(documentRoot.get(documentPage.getSortBy())));
        }
    }

    private Pageable getPageable(DocumentPage documentPage) {
        Sort sort = Sort.by(documentPage.getSortDirection(), documentPage.getSortBy());
        return PageRequest.of(documentPage.getPageNumber(), documentPage.getPageSize(), sort);
    }

    private long getDocumentsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Document> countRoot = countQuery.from(Document.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
