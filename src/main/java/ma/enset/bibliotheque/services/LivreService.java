package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.LivreDTO;
import java.util.List;

public interface LivreService {
    LivreDTO createLivre(LivreDTO dto);
    LivreDTO modifierLivre(Long id, LivreDTO dto);
    List<LivreDTO> getAllLivres();
    void supprimerLivre(Long id);
}
