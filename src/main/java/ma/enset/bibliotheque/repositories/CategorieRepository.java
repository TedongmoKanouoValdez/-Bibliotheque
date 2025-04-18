package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Categorie;
import ma.enset.bibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}