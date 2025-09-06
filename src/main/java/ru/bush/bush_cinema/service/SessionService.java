package ru.bush.bush_cinema.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bush.bush_cinema.service.exceptions.SitReserveException;
import ru.bush.bush_cinema.repository.entities.SitState;
import ru.bush.bush_cinema.repository.SessionRepository;
import ru.bush.bush_cinema.repository.SitRepository;
import ru.bush.bush_cinema.repository.entities.SessionEntity;
import ru.bush.bush_cinema.service.exceptions.NoSessionException;

@Service
@AllArgsConstructor
public class SessionService {

    private SessionRepository sessionRepository;
    private SitRepository sitRepository;
    private Cart cart;

    public SessionEntity getSession(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new NoSessionException("Сеанс не найден"));
    }

    @Transactional
    public void reserveSit(String id) {
        var sit = sitRepository.findById(id)
                .orElseThrow(() -> new SitReserveException("Место не найдено"));
        if (sit.getState() == SitState.FREE) {
            sit.setState(SitState.BOOKED);
            cart.addSit(sit);
        } else {
            throw new SitReserveException("Место занято");
        }
    }
}
