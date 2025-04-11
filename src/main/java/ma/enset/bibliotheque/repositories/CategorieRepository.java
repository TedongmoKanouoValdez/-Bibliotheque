package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}