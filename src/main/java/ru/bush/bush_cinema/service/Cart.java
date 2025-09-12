package ru.bush.bush_cinema.service;

import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ru.bush.bush_cinema.repository.entities.SitState;
import ru.bush.bush_cinema.repository.SitRepository;
import ru.bush.bush_cinema.repository.entities.SitEntity;
import ru.bush.bush_cinema.service.exceptions.SitCancelException;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class Cart {

    @Getter
    private final List<SitEntity> sits = new ArrayList<>();
    @Autowired
    private SitRepository sitRepository;

    public void addSit(SitEntity sit) {
        sits.add(sit);
    }

    @PreDestroy
    private void destroy() {
        sits.forEach(s -> s.setState(SitState.FREE));
        sitRepository.saveAll(sits);
    }

    public void cancelSit(String id) {
        var sit = sits.stream()
                .filter(sitEntity -> sitEntity.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new SitCancelException("Место в корзине не найдено"));
        sits.remove(sit);
        sit.setState(SitState.FREE);
        sitRepository.save(sit);
    }
}
