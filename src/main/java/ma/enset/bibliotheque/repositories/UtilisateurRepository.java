package ma.enset.bibliotheque.repositories;

import ma.enset.bibliotheque.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    //Optional<Utilisateur> findByEmailAndPassword(String email, String password);
    Utilisateur findFirstByEmail(String email);
    List<Utilisateur> findByRoleIn(List<String> roles);

}



