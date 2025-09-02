package ru.bush.bush_cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bush.bush_cinema.model.session.SessionFull;
import ru.bush.bush_cinema.model.session.Sit;
import ru.bush.bush_cinema.model.session.SitReserveException;
import ru.bush.bush_cinema.model.session.SitState;
import ru.bush.bush_cinema.repository.SessionMemoryRepo;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class SessionService {

    private SessionMemoryRepo repository;
    private Cart cart;

    public SessionFull getSession(String id) {
        var session = repository.getSession(id);
        if (session == null) {
            session = createSession(id);
            repository.addSession(session);
        }
        return session;
    }

    public void reserveSit(String sessionId, int col, int row) {
        var session = repository.getSession(sessionId);
        if (session == null) {
            throw new SitReserveException("Сеанс не найден");
        }
        var sit = session.getSits().stream()
                .filter(s -> s.getCol() == col && s.getRow() == row)
                .findFirst()
                .orElseThrow(() -> new SitReserveException("Место не найдено"));
        if (sit.getState() == SitState.FREE) {
            sit.setState(SitState.BOOKED);
            cart.addSit(sit);
        } else {
            throw new SitReserveException("Место занято");
        }
    }

    private SessionFull createSession(String id) {
        var sits = new ArrayList<Sit>();
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 3; j++) {
                sits.add(Sit.builder().col(i).row(j).state(SitState.FREE).build());
            }
        }
        return SessionFull.builder()
                .id(id)
                .time(id.split("-")[1])
                .sits(sits)
                .build();
    }
}
