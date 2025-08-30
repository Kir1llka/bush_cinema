package ru.bush.bush_cinema.model.Movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Movie {
    private int id;
    private String name;
    private int duration;
    private String genre;
    private String imageLink;
}
