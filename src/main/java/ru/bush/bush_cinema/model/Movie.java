package ru.bush.bush_cinema.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Movie {
    private int id;
    private String name;
    private int duration;
    private List<String> genre;
    private String imageLink;
}
