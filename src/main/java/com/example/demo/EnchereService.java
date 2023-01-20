package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Service
public class EnchereService {
    @Autowired
    EnchereRepository eRep;

    public ArrayList<Enchere> getAll(){
        ArrayList<Enchere> encheres = new ArrayList<Enchere>();
        for(Enchere enchere : eRep.findAll()){
            encheres.add(enchere);

        }
        return encheres;
    }
    public void ajouter(Enchere v){
        eRep.save(v);
    }



    public ArrayList<Enchere> getEnchereEnCours() {
        ArrayList<Enchere> liste = new ArrayList<>();
        for(Enchere m : eRep.getAllEnCours()) {
            liste.add(m);
        }
        return liste;
    }

    public ArrayList<Enchere> getEnchereFini() {
        ArrayList<Enchere> liste = new ArrayList<>();
        for(Enchere m : eRep.getAllFini()) {
            liste.add(m);
        }
        return liste;
    }

    public ArrayList<Enchere> getWhere(Utilisateur vv){
        ArrayList<Enchere> v=new ArrayList<>();
        for(Enchere u :eRep.findByUtilisateur(vv)){
            v.add(u);
        }
        return v;

    }
/*
    public ArrayList<Enchere> rechercheParMotCle(String search) {
        ArrayList<Enchere> v=new ArrayList<>();
        for(Enchere u :eRep.findAllByDescriptionContains(search)){
            v.add(u);
        }
        return v;
    }

    public ArrayList<Enchere> rechercheParDate(Date search) {
        ArrayList<Enchere> v=new ArrayList<>();
        for(Enchere u :eRep.findAllByDebutIs(search)){
            v.add(u);
        }
        return v;
    }
    public ArrayList<Enchere> rechercheParCateg(String search) {
        ArrayList<Enchere> v=new ArrayList<>();
        for(Enchere u :eRep.findAllByCategorie_enchereContaining(search)){
            v.add(u);
        }
        return v;
    }

    public ArrayList<Enchere> rechercheParPrix(double search) {
        ArrayList<Enchere> v=new ArrayList<>();
        for(Enchere u :eRep.findAllByMise_minimaleIs(search)){
            v.add(u);
        }
        return v;
    }

    public ArrayList<Enchere> rechercheParStatut(int search) {
        ArrayList<Enchere> v=new ArrayList<>();
        for(Enchere u :eRep.findAllByStatutEquals(search)){
            v.add(u);
        }
        return v;
    }
*/

    public ArrayList<Boolean> estFini(int iduser)
    {
        ArrayList<Boolean> b= new ArrayList<>();
        System.out.println("eto");
        for (LocalDateTime e: eRep.getFinEnchere(iduser)){

            if(e== LocalDateTime.now())
            {
                System.out.println("marina");
                b.add(false);

            }
            else {
                b.add(true);
                System.out.println("diso");
            }

        }
        return b;

    }

    public ArrayList<Enchere> getAjll(){
        ArrayList<Enchere> encheres = new ArrayList<Enchere>();
        for(Enchere enchere : eRep.findAll()){
            encheres.add(enchere);

        }
        return encheres;
    }

    public Connection getConnex() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost/enchere?user=enchere&password=enchere&ssl=false");
    }

    public ArrayList<Enchere> rechercheAv(String motCle,String debut,String categorie,String prix,String statut) throws SQLException, ClassNotFoundException {
        String query = "select mettre_enchere.*,categorie_enchere.libelle from mettre_enchere join categorie_enchere on mettre_enchere.idcategorie_enchere=categorie_enchere.idcategorie_enchere ";
       // System.out.print("TEST: "+motCle.length());
        if(motCle!="" && debut!="" && categorie!="" && prix!="" && statut!="") {
            query = query + "where description like '%"+motCle+"%' and (debut::date)='"+debut+"' and libelle like '%"+categorie+"%' and mise_minimale="+prix+" and statut="+statut;
        }
        else if(motCle=="" && debut!="" && categorie!="" && prix!="" && statut!="") {
            query = query + "where (debut::date)='"+debut+"' and libelle like '%"+categorie+"%' and mise_minimale="+prix+" and statut="+statut;
        }
        else if(motCle=="" && debut=="" && categorie!="" && prix!="" && statut!="") {
            query = query + "where libelle like '%"+categorie+"%' and mise_minimale="+prix+" and statut="+statut;
        }
        else if(motCle=="" && debut=="" && categorie=="" && prix=="" && statut!="") {
            query = query + "where statut="+statut;
        }
        else if(motCle!="" && debut!="" && categorie=="" && prix=="" && statut=="") {
            query = query + "where description like '%"+motCle+"%' and (debut::date)='"+debut;
        }
        else if(motCle!="" && debut!="" && categorie!="" && prix=="" && statut=="") {
            query = query + "where description like '%\"+motCle+\"%' and (debut::date)='"+debut+"' and libelle like '%"+categorie+"%'";
        }
        else if(motCle!="" && debut!="" && categorie!="" && prix!="" && statut=="") {
            query = query + "where description like '%\"+motCle+\"%' and (debut::date)='"+debut+"' and libelle like '%"+categorie+"%' and mise_minimale="+prix;
        }
        else if(motCle!="" && debut=="" && categorie!="" && prix=="" && statut=="") {
            query = query + "where description like '%\"+motCle+\"%' and libelle like '%"+categorie+"%'";
        }
        else if(motCle!="" && debut=="" && categorie=="" && prix!="" && statut=="") {
            query = query + "where description like '%\"+motCle+\"%' and mise_minimale="+prix;
        }
        else if(motCle!="" && debut=="" && categorie!="" && prix!="" && statut=="") {
            query = query + "where description like '%\"+motCle+\"%' and libelle like '%"+categorie+"%' and mise_minimale="+prix;
        }
        else if(motCle!="" && debut=="" && categorie!="" && prix!="" && statut!="") {
            query = query + "where description like '%\"+motCle+\"%' and libelle like '%"+categorie+"%' and mise_minimale="+prix+" and statut="+statut;
        }
        else if(motCle!="" && debut=="" && categorie=="" && prix!="" && statut!="") {
            query = query + "where description like '%\"+motCle+\"%' and mise_minimale="+prix+" and statut="+statut;
        }
        else if(motCle!="" && debut=="" && categorie=="" && prix=="" && statut!="") {
            query = query + "where description like '%\"+motCle+\"%' and statut="+statut;
        }
        else if(motCle=="" && debut!="" && categorie!="" && prix=="" && statut=="") {
            query = query + "where (debut::date)='"+debut+"' and libelle like '%"+categorie+"%'";
        }
        else if(motCle=="" && debut!="" && categorie!="" && prix!="" && statut=="") {
            query = query + "where (debut::date)='"+debut+"' and libelle like '%"+categorie+"%' and mise_minimale="+prix;
        }
        else if(motCle=="" && debut!="" && categorie=="" && prix!="" && statut=="") {
            query = query + "where (debut::date)='"+debut+"' and mise_minimale="+prix;
        }
        else if(motCle=="" && debut!="" && categorie=="" && prix=="" && statut!="") {
            query = query + "where (debut::date)='"+debut+"' and statut="+statut;
        }
        else if(motCle=="" && debut!="" && categorie=="" && prix!="" && statut!="") {
            query = query + "where (debut::date)='"+debut+"' and mise_minimale="+prix+" and statut="+statut;
        }
        else if(motCle=="" && debut=="" && categorie!="" && prix!="" && statut=="") {
            query = query + "where libelle like '%\"+categorie+\"%' and mise_minimale="+prix;
        }
        else if(motCle=="" && debut=="" && categorie!="" && prix=="" && statut!="") {
            query = query + "where libelle like '%"+categorie+"%' and statut="+statut;
        }
        else if(motCle=="" && debut=="" && categorie!="" && prix=="" && statut=="") {
            query = query + "where libelle like '%"+categorie+"%'";
        }
        else if(motCle=="" && debut!="" && categorie=="" && prix=="" && statut=="") {
            query = query + "where (debut::date)='"+debut;
        }
        else if(motCle=="" && debut=="" && categorie=="" && prix!="" && statut=="") {
            query = query + "where mise_minimale="+prix;
        }
        else if(motCle!="" && debut=="" && categorie=="" && prix=="" && statut=="") {
            query = query + "where description like '%"+motCle+"%'";
            System.out.println(1);
            System.out.println(query);
        }
        else {
            query = query;
        }
        System.out.println(query);
        ArrayList<Enchere> liste = new ArrayList<>();
        Connection con = getConnex();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Enchere e = new Enchere();
            e.setIdmettre_enchere(result.getInt(1));
            Utilisateur u = new Utilisateur();
            u.setIdutilisateur(result.getInt(2));
            e.setUtilisateur(u);
            Categorie_Enchere cE = new Categorie_Enchere();
            cE.setIdcategorie_Enchere(result.getInt(3));
            e.setDebut(result.getTimestamp(4).toLocalDateTime());
            Duree d = new Duree();
            d.setIdduree(result.getInt(5));
            e.setDuree_heure(d);
            e.setNom(result.getString(6));
            e.setMise_minimale(result.getDouble(7));
            e.setStatut(result.getInt(8));
            e.setDescription(result.getString(9));
            cE.setLibelle(result.getString(10));
            e.setCategorie_enchere(cE);
            liste.add(e);
        }
        return liste;
    }
}
