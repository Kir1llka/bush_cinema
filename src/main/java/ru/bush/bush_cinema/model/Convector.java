package ru.bush.bush_cinema.model;

import ru.bush.bush_cinema.api.MovieData;
import ru.bush.bush_cinema.api.SessionData;

public class Convector {
    public static MovieData toMovieData(Movie movie) {
        return MovieData.builder()
                .id(movie.getId())
                .name(movie.getName())
                .duration(movie.getDuration())
                .genre(movie.getGenre())
                .year(movie.getYear())
                .imageLink(movie.getImageLink())
                .sessions(
                        movie.getSessions().stream()
                                .map(s -> SessionData.builder().id(s.getId()).time(s.getTime()).build())
                                .toList()
                )
                .build();
    }
}
