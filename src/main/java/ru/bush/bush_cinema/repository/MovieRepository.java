package ru.bush.bush_cinema.repository;

import ru.bush.bush_cinema.model.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> getAllMovies();

    Movie getMovie(int id);

    void deleteMovie(int id);

    void putMovie(Movie movie);

    void clear();
}
