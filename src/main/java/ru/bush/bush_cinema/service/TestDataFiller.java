package ru.bush.bush_cinema.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.bush.bush_cinema.repository.MovieRepository;
import ru.bush.bush_cinema.repository.entities.MovieEntity;
import ru.bush.bush_cinema.repository.entities.SitEntity;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class TestDataFiller {

    private MovieRepository movieRepository;

    @PostConstruct
    private void fill() {
        try {
            var m = ResourcesReader.readJson("templates/movies.json", Movies.class);
            m.movies.forEach(mov -> mov.getSessions()
                    .forEach(s -> {
                        s.setMovie(mov);
                        s.setSits(m.sits.stream()
                                .map(sit -> new SitEntity(sit.getId(), sit.getCol(), sit.getRow(), sit.getState()))
                                .toList());
                    }));
            movieRepository.saveAll(m.movies);
        } catch (Exception e) {
            log.error("Can not load test data", e);
        }
    }

    /**
     * Нужен, что бы прочитать данные из json-а
     */
    public static class Movies {
        public List<MovieEntity> movies;
        public List<SitEntity> sits;
    }
}
