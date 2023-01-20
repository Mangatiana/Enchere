package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class MouvementService {
    @Autowired
    MouvementRep mr;
    @Autowired
    EncherirRepo er;


    public Connection getConnex() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost/enchere?user=enchere&password=enchere&ssl=false");
    }

    public void rencherir(Encherir newProp) throws SQLException, ClassNotFoundException /*,SurEnchereMontantException, NotEnoughSoldException*/ {
        if(compare(newProp)==true) {
            //DatabaseConnection dbc = new DatabaseConnection();
            Connection connection = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                connection = getConnex();
                Encherir enchr = new Encherir();
                Utilisateur u = new Utilisateur();
                double propMontant = newProp.getMontant();
                System.out.println(propMontant);
                if (verifySolde(newProp.getUtilisateur(), propMontant) == true) {

                    connection.setAutoCommit(false);
                    stmt = connection.prepareStatement("select * from preview_transact where idmettre_enchere=? order by montant desc limit 1");
                    stmt.setInt(1, newProp.getMettre_enchere().getIdmettre_enchere());
                    int preview_transact_account_id = 0;
                    int preview_transact_mvt_id = 0;
                    double preview_transact_montant = 0.0;
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        preview_transact_mvt_id = rs.getInt("idmouvement");
                        preview_transact_account_id = rs.getInt("idutilisateur");
                        preview_transact_montant = rs.getDouble("montant");

                        stmt = connection.prepareStatement("update utilisateur set solde = solde + ? where idutilisateur = ?");
                        stmt.setDouble(1, preview_transact_montant);
                        stmt.setInt(2, preview_transact_account_id);
                        stmt.executeUpdate();

                        stmt = connection.prepareStatement("update mouvementt set statut=1 where idmouvement=?");
                        stmt.setInt(1, preview_transact_mvt_id);
                        stmt.executeUpdate();
                    }

                    stmt = connection.prepareStatement("update utilisateur set solde = solde - ? where idutilisateur = ?");
                    stmt.setDouble(1, propMontant);
                    stmt.setInt(2, newProp.getUtilisateur().getIdutilisateur());
                    stmt.executeUpdate();

                    stmt = connection.prepareStatement("insert into mouvementt(montant,idutilisateur,idmettre_enchere) values (?,?,?)");
                    stmt.setDouble(1, propMontant);
                    stmt.setInt(2, newProp.getUtilisateur().getIdutilisateur());
                    stmt.setInt(3, newProp.getMettre_enchere().getIdmettre_enchere());
                    stmt.executeUpdate();

                    stmt = connection.prepareStatement("insert into encherir(idmettre_enchere,idutilisateur,montant) values (?,?,?)");
                    stmt.setInt(1, newProp.getMettre_enchere().getIdmettre_enchere());
                    stmt.setInt(2, newProp.getUtilisateur().getIdutilisateur());
                    stmt.setDouble(3, propMontant);
                    stmt.executeUpdate();



                    connection.commit();
                }
                else {
                    System.out.println("Votre solde est insuffisant");
                }
            }
            catch(SQLException | ClassNotFoundException e){
                    connection.rollback();
                    throw e;
                } finally{
                    if (connection != null && !connection.isClosed()) connection.close();
                    if (stmt != null && !stmt.isClosed()) stmt.close();
                    if (rs != null && !rs.isClosed()) rs.close();
                }
        }
        else {
            System.out.println("Le montant est inferieur au montant precedent");

        }
    }

    private Boolean compare(Encherir newProp) /*throws SurEnchereMontantException*/ {
        Encherir lastProp = er.findLastProp(newProp.getMettre_enchere().getIdmettre_enchere());
        if(lastProp != null && newProp.getMontant() <= lastProp.getMontant()) {
            // throw new SurEnchereMontantException("montant :"+newProp.getMontant()+
            return false;
        }
        else {
            return true;
        }
    }

    private Boolean verifySolde(Utilisateur utilisateur, double montantProp) /*throws NotEnoughSoldException*/ {
System.out.println(utilisateur.getSolde()+"solde sa ty"+montantProp);
        if(utilisateur.getSolde() < montantProp) {
            //throw new NotEnoughSoldException("votre solde est insuffisant pour effectuer cette transaction");
            System.out.println("votre solde est insuffisant pour effectuer cette transaction");
            return false;
        }
        else {
            return true;
        }
    }

}
