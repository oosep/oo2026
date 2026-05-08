package ee.joosep.decathlon.repository;

import ee.joosep.decathlon.entity.Tulemus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TulemusRepository extends JpaRepository<Tulemus, Long> {
    List<Tulemus> findBySportlaneId(Long sportlaneId);
}