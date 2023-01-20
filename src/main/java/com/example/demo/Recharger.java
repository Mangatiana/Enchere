package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="rechargement")
@NoArgsConstructor
public class      Recharger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrechargement", nullable=false)
    private int idrecharger;

    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "idutilisateur",referencedColumnName = "idutilisateur")
    private Utilisateur utilisateur;


    @Column(name = "montant", nullable=false)
    private double montant;

    @Column(name = "date_rechargement", nullable=false)
    private LocalDate date_rechargement=LocalDate.now();

    @Column(name = "etat_validation", nullable=false)
    private int etat_validation;
}
