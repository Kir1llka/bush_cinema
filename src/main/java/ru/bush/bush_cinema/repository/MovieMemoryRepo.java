package ru.bush.bush_cinema.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.bush.bush_cinema.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieMemoryRepo implements MovieRepository {

    private final List<Movie> MOVIES = new ArrayList<>();

    @PostConstruct
    private void fill() {
        MOVIES.add(Movie.builder().id(1).name("Мстители").duration(136).imageLink("link1").build());
        MOVIES.add(Movie.builder().id(2).name("Дедпул").duration(136).imageLink("link2").build());
        MOVIES.add(Movie.builder().id(3).name("Интерстеллар").duration(136).imageLink("link3").build());
    }

    @Override
    public List<Movie> getAllMovies() {
        return MOVIES;
    }

    @Override
    public Movie getMovie(int id) {
        return MOVIES.stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteMovie(int id) {
        var m = getMovie(id);
        if (m != null) {
            MOVIES.remove(m);
        }
    }

    @Override
    public void putMovie(Movie movie) {
        MOVIES.add(movie);
    }

    @Override
    public void clear() {
        MOVIES.clear();
    }
}
