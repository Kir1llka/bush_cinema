package ru.bush.bush_cinema.model.session;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SessionFull {
    private String id;
    private String time;
    private List<Sit> sits;
}
