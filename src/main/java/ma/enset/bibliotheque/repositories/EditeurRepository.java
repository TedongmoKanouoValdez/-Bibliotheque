package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeurRepository extends JpaRepository<Editeur, Long> {
}