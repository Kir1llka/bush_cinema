package ru.bush.bush_cinema.api.movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Информация о фильме")
public class MovieData {

    @Schema(description = "ID фильма", example = "3")
    private Long id;

    @Schema(description = "Название", example = "Дюна")
    private String name;

    @Schema(description = "Длительность", example = "136")
    private int duration;

    @Schema(description = "Жанры", example = "Фантастика, Драма")
    private String genre;

    @Schema(description = "Год выпуска", example = "2025")
    private int year;

    @Schema(description = "Ссылка на картинку", example = "SUCCESS")
    private String imageLink;

    @Schema(description = "Описание фильма", example = "Самый лучший детектив")
    private String description;

    private List<SessionData> sessions;
}
