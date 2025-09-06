package ru.bush.bush_cinema.api;

import ru.bush.bush_cinema.api.movie.MovieData;
import ru.bush.bush_cinema.api.movie.SessionData;
import ru.bush.bush_cinema.api.session.SessionFullData;
import ru.bush.bush_cinema.api.session.SitData;
import ru.bush.bush_cinema.repository.entities.SitState;
import ru.bush.bush_cinema.repository.entities.MovieEntity;
import ru.bush.bush_cinema.repository.entities.SessionEntity;
import ru.bush.bush_cinema.repository.entities.SitEntity;

import java.util.List;

public class Convector {
    public static MovieData toMovieData(MovieEntity movie) {
        return MovieData.builder()
                .id(movie.getId())
                .name(movie.getName())
                .duration(movie.getDuration())
                .genre(movie.getGenre())
                .year(movie.getYear())
                .imageLink(movie.getImageLink())
                .description(movie.getDescription())
                .sessions(movie.getSessions().stream().map(Convector::toSessionData).toList())
                .build();
    }

    public static SessionData toSessionData(SessionEntity session) {
        return SessionData.builder()
                .id(session.getId())
                .time(session.getTime())
                .build();
    }

    public static SessionFullData toSessionFullData(SessionEntity session, List<SitEntity> inCartSits) {
        return SessionFullData.builder()
                .id(session.getId())
                .time(session.getTime())
                .sits(session.getSits().stream().map(sit -> toSitData(sit, inCartSits)).toList())
                .build();
    }

    public static SitData toSitData(SitEntity sit, List<SitEntity> inCartSits) {
        var state = inCartSits.stream().anyMatch(s -> s.getCol() == sit.getCol() && s.getRow() == sit.getRow()) ?
                SitState.IN_CART : sit.getState();
        return SitData.builder()
                .id(sit.getId())
                .col(sit.getCol())
                .row(sit.getRow())
                .state(state)
                .build();
    }
}
