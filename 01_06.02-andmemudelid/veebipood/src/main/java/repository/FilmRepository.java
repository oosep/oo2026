package ee.joosep.veebipood.repository;

import ee.joosep.veebipood.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}