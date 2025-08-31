package ru.bush.bush_cinema.model;

import ru.bush.bush_cinema.api.MovieData;

public class Convector {
    public static MovieData toMovieData(Movie movie) {
        return MovieData.builder()
                .id(movie.getId())
                .name(movie.getName())
                .duration(movie.getDuration())
                .genre(movie.getGenre())
                .imageLink(movie.getImageLink())
                .build();
    }
}
