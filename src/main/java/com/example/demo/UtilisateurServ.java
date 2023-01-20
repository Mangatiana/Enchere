package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UtilisateurServ {
    @Autowired
    UtlisateurRep ur;

    public ArrayList<Utilisateur> getAll() {
        ArrayList<Utilisateur> list = new ArrayList<>();
        for(Utilisateur u: ur.findAll()) {
            list.add(u);
        }
        return list;
    }
    private static String Sha1(String password) throws UnsupportedEncodingException {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public  static String genererToken(String user){
        String name=user;
        LocalDateTime ajd=LocalDateTime.now();
        Integer Y=ajd.getYear();
        String taona=Y.toString();
        Integer m=ajd.getMonthValue();
        String volana=m.toString();
        Integer d=ajd.getDayOfMonth();
        String andro=d.toString();
        Integer min=ajd.getHour();
        String lera=min.toString();
        String hash="";
        try {
            String hash1=UtilisateurServ.Sha1(name);
            String h2=UtilisateurServ.Sha1(taona+volana+andro+lera);
            hash=UtilisateurServ.Sha1(hash1+h2);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UtilisateurServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }

    public Boolean check_token_si_valide(String nom){
        Boolean v=false;
        String token=this.genererToken(nom);
        ArrayList<Utilisateur> liste = this.getAll();
        for(int i=0; i<liste.size(); i++){
            if((liste.get(i).getNom().equals(nom)&&(liste.get(i).getToken().equals(token)))){
                v=true;
                break;
            }
        }
        return v;
    }

    public String log(String mail, String mdp){
        ArrayList<Utilisateur> liste=this.getAll();
        Boolean v=false;
        String t="null";
        for(int i=0; i<liste.size(); i++){
            if((liste.get(i).getEmail().equals(mail))&&(liste.get(i).getMotdepasse().equals(mdp))){
                v=true;
                Utilisateur u=new Utilisateur();
                u.setIdutilisateur(liste.get(i).getIdutilisateur());
                u.setNom(liste.get(i).getNom());
                u.setPrenom(liste.get(i).getPrenom());
                u.setDatenaissance(liste.get(i).getDatenaissance());
                u.setEmail(mail);
                u.setMotdepasse(mdp);
                u.setSolde(liste.get(i).getSolde());
                String token=this.genererToken(mail);
                u.setToken(token);
                t=u.getToken();
                //urepo.save(u);
            }
        }
        return t;
    }

    public Boolean deco(String mail,String mdp){
        ArrayList<Utilisateur> liste=this.getAll();
        Boolean v=false;
        for(int i=0; i<liste.size(); i++){
            if((liste.get(i).getEmail().equals(mail)&&(liste.get(i).getMotdepasse().equals(mdp)))){
                v=true;
                Utilisateur u=new Utilisateur();
                u.setIdutilisateur(liste.get(i).getIdutilisateur());
                u.setNom(liste.get(i).getNom());
                u.setPrenom(liste.get(i).getPrenom());
                u.setDatenaissance(liste.get(i).getDatenaissance());
                u.setEmail(mail);
                u.setMotdepasse(mdp);
                u.setSolde(liste.get(i).getSolde());
                ur.save(u);
            }
        }
        return v;
    }


    public void inscription(Utilisateur v){
        ur.save(v);
    }


}
