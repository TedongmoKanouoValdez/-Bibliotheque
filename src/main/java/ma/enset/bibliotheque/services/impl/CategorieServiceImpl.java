package ma.enset.bibliotheque.services.impl;

import lombok.AllArgsConstructor;
import ma.enset.bibliotheque.dtos.CategorieDTO;
import ma.enset.bibliotheque.entities.Categorie;
import ma.enset.bibliotheque.mappers.CategorieMapper;
import ma.enset.bibliotheque.repositories.CategorieRepository;
import ma.enset.bibliotheque.services.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    @Override
    public CategorieDTO createCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = categorieMapper.toEntity(categorieDTO);
        Categorie savedCategorie = categorieRepository.save(categorie);
        return categorieMapper.toDto(savedCategorie);
    }

    @Override
    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll()
                .stream()
                .map(categorieMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieDTO getCategorieById(Long id) {
        return categorieRepository.findById(id)
                .map(categorieMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvée"));
    }

    @Override
    public CategorieDTO updateCategorie(Long id, CategorieDTO categorieDTO) {
        Categorie existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvée"));

        existingCategorie.setLibelle(categorieDTO.getLibelle());
        existingCategorie.setDescription(categorieDTO.getDescription());

        Categorie updatedCategorie = categorieRepository.save(existingCategorie);
        return categorieMapper.toDto(updatedCategorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
