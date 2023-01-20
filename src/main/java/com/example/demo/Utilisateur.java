package com.example.demo;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "Utilisateur")
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idutilisateur", nullable=false)
    private int idutilisateur;

    @Column(name = "nom", nullable=false)
    private String nom;

    @Column(name = "prenom", nullable=false)
    private String prenom;

    @Column(name = "date_naissance", nullable=true )
    private Date datenaissance;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "motdepasse", nullable=false)
    private String motdepasse;

    @Column(name = "solde" , nullable=true,updatable = true)
    private Double solde;

    @Column(name = "token" , nullable=true,updatable = true)
    private String token;
}
