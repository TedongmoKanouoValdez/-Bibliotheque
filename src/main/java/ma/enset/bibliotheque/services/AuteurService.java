package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.AuteurDTO;

import java.util.List;

public interface AuteurService {
    AuteurDTO createAuteur(AuteurDTO auteurDTO);

    // Méthode pour modifier un auteur
    AuteurDTO modifierAuteur(Long id, AuteurDTO auteurDTO);  // Changez la signature i

    List<AuteurDTO> getAllAuteurs();

    void supprimerAuteur(Long id);

}
