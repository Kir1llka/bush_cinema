package ru.bush.bush_cinema.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieData {
    private int id;
    private String name;
    private int duration;
    private String genre;
    private int year;
    private String imageLink;
    private List<SessionData> sessions;
}
