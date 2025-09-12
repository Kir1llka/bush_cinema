package ru.bush.bush_cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bush.bush_cinema.service.exceptions.NoMovieException;
import ru.bush.bush_cinema.repository.MovieRepository;
import ru.bush.bush_cinema.repository.entities.MovieEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository repository;

    public List<MovieEntity> getAllMovies() {
        return repository.findAll();
    }

    public MovieEntity getMovie(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoMovieException("Такого фильма нет"));
    }
}
