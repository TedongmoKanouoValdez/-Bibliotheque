package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.CategorieDTO;

import java.util.List;

public interface CategorieService {
    CategorieDTO saveCategorie(CategorieDTO dto);
    List<CategorieDTO> getAllCategories();
    CategorieDTO getCategorie(Long id);
    void deleteCategorie(Long id);
}
