package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    //Avoir l'historiques des emprunts de chaque utilisateur par ordre de date
    List<Emprunt> findByUtilisateurIdOrderByDateEmpruntDesc(Long utilisateurId);

}
