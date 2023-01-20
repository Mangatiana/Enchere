package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncherirServ {
    @Autowired
    EncherirRepo eR;

    public void enchr(Encherir e) {
        eR.save(e);
    }
}
