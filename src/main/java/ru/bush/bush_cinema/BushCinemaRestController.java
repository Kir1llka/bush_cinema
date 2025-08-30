package ru.bush.bush_cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bush.bush_cinema.model.Movie.Movie;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
public class BushCinemaRestController {

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return List.of(
                Movie.builder().id(1).name("Мстители").duration(136).imageLink("link1").build(),
                Movie.builder().id(2).name("Дедпул").duration(136).imageLink("link2").build(),
                Movie.builder().id(2).name("Интерстеллар").duration(136).imageLink("link3").build()
        );
    }
}
