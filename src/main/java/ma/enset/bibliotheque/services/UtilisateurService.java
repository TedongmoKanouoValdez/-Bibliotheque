package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.UtilisateurDTO;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDTO saveUtilisateur(UtilisateurDTO utilisateurDTO);
    List<UtilisateurDTO> getAllUtilisateurs();
}
