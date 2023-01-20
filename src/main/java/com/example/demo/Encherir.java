package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "encherir")
@NoArgsConstructor
public class Encherir {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idencherir", nullable=false)
    private int idencherir;

    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "idutilisateur",referencedColumnName = "idutilisateur")
    private Utilisateur utilisateur;

    @ManyToOne(targetEntity = Enchere.class)
    @JoinColumn(name = "idmettre_enchere",referencedColumnName = "idmettre_enchere")
    private Enchere mettre_enchere;

    @Column(name = "montant", nullable=false)
    private double montant;
}
