package ru.bush.bush_cinema.api.session;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SessionFullData {
    private String id;
    private String time;
    private List<SitData> sits;
}
