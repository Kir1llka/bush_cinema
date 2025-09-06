package ru.bush.bush_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bush.bush_cinema.repository.entities.SitEntity;

public interface SitRepository extends JpaRepository<SitEntity, String> {
}
