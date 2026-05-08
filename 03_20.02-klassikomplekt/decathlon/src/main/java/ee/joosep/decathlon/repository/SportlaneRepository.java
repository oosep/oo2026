package ee.joosep.decathlon.repository;

import ee.joosep.decathlon.entity.Sportlane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportlaneRepository extends JpaRepository<Sportlane, Long> {
}