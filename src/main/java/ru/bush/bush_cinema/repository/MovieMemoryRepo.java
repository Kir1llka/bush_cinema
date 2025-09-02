package ru.bush.bush_cinema.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.bush.bush_cinema.model.movie.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieMemoryRepo implements MovieRepository {

    private final List<Movie> MOVIES = new ArrayList<>();

    @PostConstruct
    private void fill() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            ClassPathResource resource = new ClassPathResource("templates/movies.json");
            var m = objectMapper.readValue(resource.getInputStream(), Movies.class);
            MOVIES.addAll(List.of(m.movies));
        } catch (IOException e) {
            e.printStackTrace(); //todo
        }
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

    /**
     * Нужен, что бы прочитать данные из json-а
     */
    public static class Movies {
        public Movie[] movies;
    }
}
