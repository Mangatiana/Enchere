package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere,Integer>{
    @Query(value = "select * from mettre_enchere where statut=0",nativeQuery = true)
    List<Enchere> getAllEnCours();

    @Query(value = "select * from mettre_enchere where statut=1",nativeQuery = true)
    List<Enchere> getAllFini();

    List<Enchere> findByUtilisateur(Utilisateur utilisateur);

    @Query(value = "select debut+valeur*INTERVAL '1 hour' from v_dureeenchere where idutilisateur=:id",nativeQuery = true)
    List<LocalDateTime> getFinEnchere(@Param("id") int id);
/*
    //@Query(value = "select * from mettre_enchere where description='%s%'",nativeQuery = true)
    List<Enchere> findAllByDescriptionContains(String search);

    @Query(value = "select mettre_enchere.*,categorie_enchere.* from mettre_enchere join categorie_enchere on mettre_enchere.idcategorie_enchere=categorie_enchere.idcategorie_enchere where libelle like %:search%",nativeQuery = true )
    List<Enchere> findAllByCategorie_enchereContaining(String search);

   @Query(value = "select * from mettre_enchere where (debut::date)= :search",nativeQuery = false)
    List<Enchere> findAllByDebutIs(Date search);


   @Query(value = "select * from mettre_enchere where mise_minimale= :search",nativeQuery = true)
    List<Enchere> findAllByMise_minimaleIs(double search);

    @Query(value = "select * from mettre_enchere where statut= :search",nativeQuery = true)
    List<Enchere> findAllByStatutEquals(int search);

 */
}
