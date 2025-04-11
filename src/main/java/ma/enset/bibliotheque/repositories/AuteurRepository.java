package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}