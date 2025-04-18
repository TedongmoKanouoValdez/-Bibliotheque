package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.LivreDTO;

import java.util.List;

public interface LivreService {
    LivreDTO createLivre(LivreDTO dto);
    LivreDTO getLivreById(Long id);
    List<LivreDTO> getAllLivres();
    LivreDTO updateLivre(Long id, LivreDTO dto);
    void deleteLivre(Long id);

    void addCategorieToLivre(Long livreId, Long categorieId);

}
