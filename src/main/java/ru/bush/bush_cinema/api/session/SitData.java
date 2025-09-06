package ru.bush.bush_cinema.api.session;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.bush.bush_cinema.repository.entities.SitState;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Данные о месте на сеансе")
public class SitData {

    @Schema(description = "ID места", example = "9e659638-cd75-4ebf-97b9-461076e71f42")
    private String id;

    @Schema(description = "Ряд", example = "1")
    private int col;

    @Schema(description = "Место", example = "2")
    private int row;

    @Schema(description = "Состояние места", example = "FREE")
    private SitState state;
}
