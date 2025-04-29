package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.UtilisateurDTO;
import ma.enset.bibliotheque.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDTO saveUtilisateur(UtilisateurDTO utilisateurDTO);
    List<UtilisateurDTO> getAllUtilisateurs();
    List<UtilisateurDTO> getUtilisateursParRoles(List<String> roles);
    UtilisateurDTO deleteUtilisateur(Long id);
    UtilisateurDTO updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO);
}
