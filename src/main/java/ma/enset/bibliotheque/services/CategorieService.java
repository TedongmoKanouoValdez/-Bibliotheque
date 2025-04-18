package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.CategorieDTO;

import java.util.List;

public interface CategorieService {
    CategorieDTO createCategorie(CategorieDTO dto); // renommé pour correspondre à l'implémentation
    List<CategorieDTO> getAllCategories();
    CategorieDTO getCategorieById(Long id);
    CategorieDTO updateCategorie(Long id, CategorieDTO dto);
    void deleteCategorie(Long id);
}
