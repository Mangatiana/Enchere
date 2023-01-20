package com.example.demo;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="mettre_enchere_photo")
@NoArgsConstructor
public class Photo {
    @ManyToOne(targetEntity = Enchere.class)
    @JoinColumn(name = "idmettre_enchere",referencedColumnName = "idmettre_enchere")
    private Enchere enchere;

    @Column(name = "photo" , nullable=false)
    private String photo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idphoto", nullable=false)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
