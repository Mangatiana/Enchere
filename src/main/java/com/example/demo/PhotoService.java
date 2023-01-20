package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class PhotoService   {

    PhotoRepository eRep;

    public ArrayList<Photo> getAll(){
        ArrayList<Photo> photos = new ArrayList<Photo>();
        for(Photo photo : eRep.findAll()){
            photos.add(photo);
        }
        return photos;
    }

    public ArrayList<Photo> getWhere(Enchere vv){
        ArrayList<Photo> v=new ArrayList<>();
        for(Photo u :eRep.findByEnchere(vv)){
            v.add(u);
        }
        return v;

    }
}
