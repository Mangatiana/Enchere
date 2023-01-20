package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="duree")
@NoArgsConstructor
public class Duree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idduree", nullable=false)
    private int idduree;

    @Column(name = "valeur", nullable=false)
    private double valeur;

}
