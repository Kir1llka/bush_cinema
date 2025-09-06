package ru.bush.bush_cinema.model;

import ru.bush.bush_cinema.api.movie.MovieData;
import ru.bush.bush_cinema.api.movie.SessionData;
import ru.bush.bush_cinema.api.session.SessionFullData;
import ru.bush.bush_cinema.api.session.SitData;
import ru.bush.bush_cinema.model.movie.Movie;
import ru.bush.bush_cinema.model.movie.Session;
import ru.bush.bush_cinema.model.session.SessionFull;
import ru.bush.bush_cinema.model.session.Sit;
import ru.bush.bush_cinema.model.session.SitState;

import java.util.List;

public class Convector {
    public static MovieData toMovieData(Movie movie) {
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

    public static SessionData toSessionData(Session session) {
        return SessionData.builder()
                .id(session.getId())
                .time(session.getTime())
                .build();
    }

    public static SessionFullData toSessionFullData(SessionFull session, List<Sit> inCartSits) {
        return SessionFullData.builder()
                .id(session.getId())
                .time(session.getTime())
                .sits(session.getSits().stream().map(sit -> toSitData(sit, inCartSits)).toList())
                .build();
    }

    public static SitData toSitData(Sit sit, List<Sit> inCartSits) {
        var state = inCartSits.stream().anyMatch(s -> s.getCol() == sit.getCol() && s.getRow() == sit.getRow()) ?
                SitState.IN_CART : sit.getState();
        return SitData.builder()
                .col(sit.getCol())
                .row(sit.getRow())
                .state(state)
                .build();
    }
}
