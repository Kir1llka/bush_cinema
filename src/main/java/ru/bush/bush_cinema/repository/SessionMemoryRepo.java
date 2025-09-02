package ru.bush.bush_cinema.repository;

import org.springframework.stereotype.Repository;
import ru.bush.bush_cinema.model.session.SessionFull;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionMemoryRepo {
    private final Map<String, SessionFull> SESSIONS = new HashMap<>();

    public SessionFull getSession(String id) {
        return SESSIONS.getOrDefault(id, null);
    }

    public void addSession(SessionFull session) {
        SESSIONS.put(session.getId(), session);
    }
}
