package ru.bush.bush_cinema.api.session;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.bush.bush_cinema.model.session.SitState;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Данные о месте на сеансе")
public class SitData {

    @Schema(description = "Ряд", example = "1")
    private int col;

    @Schema(description = "Место", example = "2")
    private int row;

    @Schema(description = "Состояние места", example = "FREE")
    private SitState state;
}
