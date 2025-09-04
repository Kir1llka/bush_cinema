package ru.bush.bush_cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bush.bush_cinema.model.movie.Movie;
import ru.bush.bush_cinema.repository.MovieRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository repository;

    public List<Movie> getAllMovies() {
        return repository.getAllMovies();
    }

    public Movie getMovie(int id) {
        return repository.getMovie(id);
    }
}
