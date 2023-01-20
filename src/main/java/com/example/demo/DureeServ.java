package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DureeServ {
    @Autowired
    DureeRep dr;

    public void ajoutDuree(Duree duree) { dr.save(duree); }

    public ArrayList<Duree> getAll() {
        ArrayList<Duree> durees = new ArrayList<Duree>();
        for(Duree duree : dr.findAll()){
            durees.add(duree);

        }
        return durees;
    }
}
