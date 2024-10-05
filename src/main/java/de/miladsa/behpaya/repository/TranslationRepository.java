package de.miladsa.behpaya.repository;

import de.miladsa.behpaya.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Integer> {
}
