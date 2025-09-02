package ru.bush.bush_cinema.model.session;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sit {
    private int col;
    private int row;
    private SitState state;
}
