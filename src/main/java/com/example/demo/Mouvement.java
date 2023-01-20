package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mouvementt")
@NoArgsConstructor
public class Mouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idmouvement", nullable=false)
    private int idmouvement;

    @Column(name = "montant", nullable=false)
    private double montant;

    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "idutilisateur",referencedColumnName = "idutilisateur")
    private Utilisateur utilisateur;

    @ManyToOne(targetEntity = Enchere.class)
    @JoinColumn(name = "idmettre_enchere",referencedColumnName = "idmettre_enchere")
    private Enchere enchere;

    @Column(name = "statut")
    private int statut;
}
