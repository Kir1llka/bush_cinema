package ru.bush.bush_cinema.api.movie;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Информация о сеансе")
public class SessionData {

    @Schema(description = "ID сеанса", example = "дюна-10:30")
    private String id;

    @Schema(description = "Время сеанса", example = "10:30")
    private String time;
}
