package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@CrossOrigin
public class EncherirContr {
    @Autowired
    EncherirServ es;
    @Autowired
    MouvementService ms;

    @PostMapping("/encherir")
    public void encherir(@RequestBody Encherir e) throws SQLException, ClassNotFoundException {
        ms.rencherir(e);
        es.enchr(e);

    }

}
