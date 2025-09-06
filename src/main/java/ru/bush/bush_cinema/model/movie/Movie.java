package ru.bush.bush_cinema.model.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private int id;
    private String name;
    private int duration;
    private String genre;
    private int year;
    private String imageLink;
    private String description;
    private List<Session> sessions;
}
