package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.EmpruntDTO;

import java.util.List;

public interface EmpruntService {
    EmpruntDTO createEmprunt(EmpruntDTO empruntDTO);
    EmpruntDTO enregistrerRetour(Long empruntId);
    List<EmpruntDTO> getHistoriqueEmprunts(Long utilisateurId);
    List<EmpruntDTO> getAllHistoriqueEmprunts();

}
