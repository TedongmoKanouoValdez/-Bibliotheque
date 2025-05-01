package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
