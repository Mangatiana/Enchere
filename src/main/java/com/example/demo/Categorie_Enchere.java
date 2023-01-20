package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "categorie_enchere")
@NoArgsConstructor
public class Categorie_Enchere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcategorie_enchere", nullable=false)
    private int idcategorie_Enchere;

    @Column(name = "libelle", nullable=false)
    private String libelle;
}
