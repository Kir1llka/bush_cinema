package ru.bush.bush_cinema.api.session;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Полная информация о сеансе")
public class SessionFullData {

    @Schema(description = "ID сеанса", example = "56")
    private Long id;

    @Schema(description = "Время сеанса", example = "10:30")
    private String time;

    private List<SitData> sits;
}
