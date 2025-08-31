package ru.bush.bush_cinema.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bush.bush_cinema.api.Result;
import ru.bush.bush_cinema.api.MovieData;
import ru.bush.bush_cinema.model.Convector;
import ru.bush.bush_cinema.service.MovieService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cinema")
public class BushCinemaRestController {

    private MovieService movieService;

    @GetMapping("/movies")
    public Result<List<MovieData>> getMovies() {
        var data = movieService.getAllMovies().stream()
                .map(Convector::toMovieData)
                .toList();
        return Result.ok(data);
    }
}
