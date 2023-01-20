package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class PhotoController {
    @Autowired
    PhotoService eS;
    @GetMapping("/photos")
    public ArrayList<Photo> getAllPhoto(){
        return eS.getAll();
    }

    @GetMapping("/photoswhere")
    public ArrayList<Photo>photowhere(@RequestParam(value="idv") int idv){
        Enchere v1=new Enchere();
        v1.setIdmettre_enchere(idv);
        return eS.getWhere(v1);
    }

}
