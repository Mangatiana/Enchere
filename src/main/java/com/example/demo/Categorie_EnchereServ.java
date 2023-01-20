package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Categorie_EnchereServ {
    @Autowired
    CategorieEnchereRep ceR;

    public ArrayList<Categorie_Enchere> getAll() {
        ArrayList<Categorie_Enchere> lCategorie = new ArrayList<>();
        for(Categorie_Enchere c : ceR.findAll()) {
            lCategorie.add(c);
        }
        return lCategorie;
    }
}
