package ru.bush.bush_cinema.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sits")
public class SitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int col;
    @Column(name = "row_number")
    private int row;

    @Enumerated(value = EnumType.STRING)
    private SitState state;
}
