package ru.bush.bush_cinema.api;

import ru.bush.bush_cinema.api.movie.MovieData;
import ru.bush.bush_cinema.api.movie.SessionData;
import ru.bush.bush_cinema.api.session.SessionFullData;
import ru.bush.bush_cinema.api.session.SitData;
import ru.bush.bush_cinema.repository.entities.SitState;
import ru.bush.bush_cinema.repository.entities.MovieEntity;
import ru.bush.bush_cinema.repository.entities.SessionEntity;
import ru.bush.bush_cinema.repository.entities.SitEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Convector {
    public static MovieData toMovieData(MovieEntity movie) {
        return MovieData.builder()
                .id(movie.getId())
                .name(movie.getName())
                .duration(movie.getDuration())
                .genre(movie.getGenre())
                .year(movie.getYear())
                .posterUrl(movie.getPosterUrl())
                .posterUrlPreview(movie.getPosterUrlPreview())
                .description(movie.getDescription())
                .sessions(movie.getSessions().stream().map(Convector::toSessionData).toList())
                .build();
    }

    private static SessionData toSessionData(SessionEntity session) {
        return SessionData.builder()
                .id(session.getId())
                .time(session.getTime())
                .build();
    }

    public static SessionFullData toSessionFullData(SessionEntity session, List<SitEntity> inCartSits) {
        return SessionFullData.builder()
                .id(session.getId())
                .movieId(session.getMovie().getId())
                .time(session.getTime())
                .sits(toSitsArr(session.getSits(), inCartSits))
                .build();
    }

    private static List<List<SitData>> toSitsArr(List<SitEntity> sits, List<SitEntity> inCartSits) {
        var result = new ArrayList<List<SitData>>();
        var innerList = new ArrayList<SitData>();

        int row = 1;
        for (var sit : sits.stream()
                .sorted(Comparator.comparing(SitEntity::getRow)
                        .thenComparing(SitEntity::getCol))
                .toList()) {
            if (sit.getRow() != row) {
                row++;
                result.add(innerList);
                innerList = new ArrayList<>();
            }
            innerList.add(toSitData(sit, inCartSits));
        }
        result.add(innerList);
        return result;
    }

    private static SitData toSitData(SitEntity sit, List<SitEntity> inCartSits) {
        var state = inCartSits.stream().anyMatch(s -> s.getId().equals(sit.getId())) ?
                SitState.IN_CART : sit.getState();
        return SitData.builder()
                .id(sit.getId())
                .col(sit.getCol())
                .row(sit.getRow())
                .state(state)
                .build();
    }
}
