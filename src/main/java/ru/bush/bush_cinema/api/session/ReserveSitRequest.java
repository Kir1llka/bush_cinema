package ru.bush.bush_cinema.api.session;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(description = "Тело запроса резервирования места")
public class ReserveSitRequest {

    @NotBlank(message = "sessionId не может быть пустым")
    @Schema(description = "ID сеанса", example = "дюна-10:30")
    private String sessionId;

    @NotNull(message = "col не может быть null")
    @Schema(description = "Ряд", example = "1")
    private Integer col;

    @NotNull(message = "row не может быть null")
    @Schema(description = "Место", example = "2")
    private Integer row;
}
