package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EncherirRepo extends JpaRepository<Encherir,Integer> {
   /* @Modifying
    @Query("update utilisateur set solde = () where idutilisateur = :idtilisateur")
    void soustraireSolde(int idutilisateur);

    @Query(value = "select * from encherir order by montant desc limit 1 where idmettre_enchere= :idmettre_enchere",nativeQuery = true)
    Encherir getMax(int idmettre_enchere);
*/

    @Query(value = "select * from encherir where idmettre_enchere= :idenchere order by idencherir desc limit 1", nativeQuery = true)
    public Encherir findLastProp(@Param("idenchere") int idenchere);

}
