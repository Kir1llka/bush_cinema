package ru.bush.bush_cinema.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import static ru.bush.bush_cinema.api.Result.ResultStatus.SUCCESS;
import static ru.bush.bush_cinema.api.Result.ResultStatus.ERROR;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Результат ответа апи")
public class Result<DATA> {
    private Meta meta;
    private DATA data;

    public Result(Meta meta, DATA data) {
        this.meta = meta;
        this.data = data;
    }

    public static <DATA> Result<DATA> ok() {
        return new Result<>(Meta.ok(), null);
    }

    public static <DATA> Result<DATA> error() {
        return new Result<>(Meta.error(), null);
    }

    public static <DATA> Result<DATA> error(String description) {
        return new Result<>(Meta.error(description), null);
    }

    public static <DATA> Result<DATA> ok(DATA data) {
        return new Result<>(Meta.ok(), data);
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "Мета информация ответа апи")
    public static class Meta {

        @Schema(description = "Статус ответа", example = "SUCCESS")
        private ResultStatus status;

        @Schema(description = "Описание ответа", example = "Запрос выполнен успешно")
        private String description;

        public static Meta ok() {
            return Meta.builder().status(SUCCESS).build();
        }

        public static Meta error() {
            return Meta.builder().status(ERROR).build();
        }

        public static Meta error(String description) {
            return Meta.builder().status(ERROR).description(description).build();
        }
    }

    public enum ResultStatus {
        SUCCESS, ERROR;
    }
}
