package ru.bush.bush_cinema.service;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ru.bush.bush_cinema.model.session.Sit;
import ru.bush.bush_cinema.model.session.SitState;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class Cart {
    private final List<Sit> sits = new ArrayList<>();

    public void addSit(Sit sit) {
        sits.add(sit);
    }

    public List<Sit> getSits() {
        return sits;
    }

    @PreDestroy
    private void destroy() {
        sits.forEach(s -> s.setState(SitState.FREE));
    }
}
