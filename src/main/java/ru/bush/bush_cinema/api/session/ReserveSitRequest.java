package ru.bush.bush_cinema.api.session;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ReserveSitRequest {
    @NotBlank(message = "sessionId не может быть пустым")
    private String sessionId;
    @NotNull(message = "col не может быть null")
    private Integer col;
    @NotNull(message = "row не может быть null")
    private Integer row;
}
