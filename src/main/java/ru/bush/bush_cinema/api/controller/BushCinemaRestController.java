package ru.bush.bush_cinema.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bush.bush_cinema.api.session.ReserveSitRequest;
import ru.bush.bush_cinema.api.Result;
import ru.bush.bush_cinema.api.movie.MovieData;
import ru.bush.bush_cinema.api.session.SessionFullData;
import ru.bush.bush_cinema.model.Convector;
import ru.bush.bush_cinema.service.Cart;
import ru.bush.bush_cinema.service.MovieService;
import ru.bush.bush_cinema.service.SessionService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cinema")
public class BushCinemaRestController {

    private MovieService movieService;
    private SessionService sessionService;
    private Cart cart;

    @GetMapping("/movies")
    public Result<List<MovieData>> getMovies() {
        var data = movieService.getAllMovies().stream()
                .map(Convector::toMovieData)
                .toList();
        return Result.ok(data);
    }

    @GetMapping("/session/{id}")
    public Result<SessionFullData> getSession(@PathVariable String id) {
        var data = Convector.toSessionFullData(sessionService.getSession(id), cart.getSits());
        return Result.ok(data);
    }

    @PostMapping("/sit/reserve")
    public Result<Void> reserveSit(@Valid @RequestBody ReserveSitRequest request) {
        sessionService.reserveSit(request.getSessionId(), request.getCol(), request.getRow());
        return Result.ok();
    }

}
