package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin
public class EnchereController {
    @Autowired
    EnchereService eS;
    @GetMapping("/encheres")
    public ArrayList<Enchere> getAllEncheres(){
        return eS.getAll();

    }

    @GetMapping("/encherefini")
    public ArrayList<Enchere> getFini(){
        return eS.getEnchereFini();

    }

    @GetMapping("/encherecours")
    public ArrayList<Enchere> getCours(){
        return eS.getEnchereEnCours();
    }

    @GetMapping("/encherewhere")
    public ArrayList<Enchere>getWhere(@RequestParam(value="idutilisateur") int idutilisateur){
        Utilisateur v1=new Utilisateur();
        v1.setIdutilisateur(idutilisateur);
        return eS.getWhere(v1);
    }



    @PostMapping("/ajouterEnchere")
    public void insert(@RequestBody Enchere e){
        eS.ajouter(e);
    }

/*
    @GetMapping("/rechercheMotclet")
    public ArrayList<Enchere> rechercheMotCle(@RequestParam(value="motcle") String motcle) {
        return eS.rechercheParMotCle(motcle);
    }

    @GetMapping("/rechercheCategorie")
    public ArrayList<Enchere> rechercheCategorie(@RequestParam(value="categorie") String categorie) {
        return eS.rechercheParCateg(categorie);
    }

    @GetMapping("/recherchePrix")
    public ArrayList<Enchere> recherchePrix(@RequestParam(value="prix") double prix) {
        return eS.rechercheParPrix(prix);
    }

    @GetMapping("/rechercheDate")
    public ArrayList<Enchere> rechercheDate(@RequestParam(value="debut") Date debut) {
        return eS.rechercheParDate(debut);
    }

    @GetMapping("/rechercheStatut")
    public ArrayList<Enchere> rechercheStatut(@RequestParam(value="debut") int statut) {
        return eS.rechercheParStatut(statut);
    }
*/
    @GetMapping("/notifier")
    public ArrayList<Boolean> notifier(@RequestParam(value="idutilisateur") int idutilisateur)
    {
        System.out.println("mbola eto");
 return eS.estFini(idutilisateur);
    }
//public ArrayList<Enchere> rechercheAv(String motCle,String debut,String categorie,String prix,String statut)
    @GetMapping("/rechercheAvance")
    public ArrayList<Enchere> notifier(@RequestParam(value="motCle") String motCle,@RequestParam(value="debut") String debut,@RequestParam(value="categorie") String categorie,@RequestParam(value="prix") String prix,@RequestParam(value="statut") String statut) throws SQLException, ClassNotFoundException {
       return eS.rechercheAv(motCle,debut,categorie,prix,statut);
    }
}
