import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@RestController
@RequestMapping("filmid")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    @PostMapping
    public List<Film> addFilm(@RequestBody Film film) {
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Film> deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
        return filmRepository.findAll();
    }
}