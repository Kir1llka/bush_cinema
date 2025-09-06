package ru.bush.bush_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bush.bush_cinema.repository.entities.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
