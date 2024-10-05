package de.miladsa.behpaya.repository;

import de.miladsa.behpaya.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {
}
