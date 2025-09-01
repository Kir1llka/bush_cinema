package ru.bush.bush_cinema.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionData {
    private String id;
    private String time;
}
