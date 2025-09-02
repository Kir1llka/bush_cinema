package ru.bush.bush_cinema.api.session;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.bush.bush_cinema.model.session.SitState;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SitData {
    private int col;
    private int row;
    private SitState state;
}
