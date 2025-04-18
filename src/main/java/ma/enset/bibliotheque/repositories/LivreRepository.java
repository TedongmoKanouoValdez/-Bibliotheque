package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    @Query("SELECT l FROM Livre l LEFT JOIN FETCH l.categories")
    List<Livre> findAllWithCategories();

}
