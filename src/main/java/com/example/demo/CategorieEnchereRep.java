package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieEnchereRep  extends JpaRepository<Categorie_Enchere,Integer> {
    List<Enchere> findAllByLibelleContains(String search);
}
