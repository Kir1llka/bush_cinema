package ru.bush.bush_cinema.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bush.bush_cinema.api.Convector;
import ru.bush.bush_cinema.api.Result;
import ru.bush.bush_cinema.api.movie.MovieData;
import ru.bush.bush_cinema.api.session.ReserveSitRequest;
import ru.bush.bush_cinema.api.session.SessionFullData;
import ru.bush.bush_cinema.service.Cart;
import ru.bush.bush_cinema.service.MovieService;
import ru.bush.bush_cinema.service.SessionService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cinema")
@Tag(name = "cinema", description = "Операции с пользовательскими апи")
public class BushCinemaRestController {

    private MovieService movieService;
    private SessionService sessionService;
    private Cart cart;

    @Operation(
            summary = "Получить все фильмы",
            description = "Возвращает список всех фильмов",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успех"),
                    @ApiResponse(responseCode = "500", description = "Что-то пошло не так",
                            content = @Content(schema = @Schema(implementation = Result.class)))
            }
    )
    @GetMapping("/movies")
    public Result<List<MovieData>> getMovies() {
        var data = movieService.getAllMovies().stream()
                .map(Convector::toMovieData)
                .toList();
        return Result.ok(data);
    }

    @Operation(
            summary = "Получить фильм по его id",
            description = "Возвращает объект фильма",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успех"),
                    @ApiResponse(responseCode = "500", description = "Что-то пошло не так",
                            content = @Content(schema = @Schema(implementation = Result.class)))
            }
    )
    @GetMapping("/movies/{id}")
    public Result<MovieData> getMovie(
            @PathVariable
            @Parameter(name = "id", description = "ID фильма", example = "1")
            Long id
    ) {
        var data = Convector.toMovieData(movieService.getMovie(id));
        return Result.ok(data);
    }

    @Operation(
            summary = "Получить сеанс по ID",
            description = "Возвращает информацию о сеансе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "404", description = "Сеанс не найден",
                            content = @Content(schema = @Schema(implementation = Result.class))),
                    @ApiResponse(responseCode = "500", description = "Что-то пошло не так",
                            content = @Content(schema = @Schema(implementation = Result.class)))
            }
    )
    @GetMapping("/session/{id}")
    public Result<SessionFullData> getSession(
            @PathVariable
            @Parameter(name = "id", description = "ID сеанса", example = "56")
            Long id
    ) {
        var data = Convector.toSessionFullData(sessionService.getSession(id), cart.getSits());
        return Result.ok(data);
    }

    @Operation(
            summary = "Резервирование места по его данным",
            description = "Возвращает статус операции",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ"),
                    @ApiResponse(responseCode = "404", description = "Место не найден",
                            content = @Content(schema = @Schema(implementation = Result.class))),
                    @ApiResponse(responseCode = "500", description = "Что-то пошло не так",
                            content = @Content(schema = @Schema(implementation = Result.class)))
            }
    )
    @PostMapping("/sit/reserve")
    public Result<Void> reserveSit(@Valid @RequestBody ReserveSitRequest request) {
        sessionService.reserveSit(request.getId());
        return Result.ok();
    }

}
