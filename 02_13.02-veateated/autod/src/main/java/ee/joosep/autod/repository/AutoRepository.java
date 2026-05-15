package ee.joosep.autod.repository;

import ee.joosep.autod.entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long> {
}