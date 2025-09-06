package ru.bush.bush_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bush.bush_cinema.repository.entities.SessionEntity;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
}
