package ru.bush.bush_cinema.api.session;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Тело запроса резервирования места")
public class ReserveSitRequest {

    @NotBlank(message = "id не может быть пустым")
    @Schema(description = "ID места", example = "cbbb583a-3183-4b42-9d21-8933ac92316b")
    private String id;
}
