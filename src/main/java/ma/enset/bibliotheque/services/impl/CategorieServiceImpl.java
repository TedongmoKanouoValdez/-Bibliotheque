package ma.enset.bibliotheque.services;

import lombok.AllArgsConstructor;
import ma.enset.bibliotheque.dtos.CategorieDTO;
import ma.enset.bibliotheque.entities.Categorie;
import ma.enset.bibliotheque.mappers.CategorieMapper;
import ma.enset.bibliotheque.repositories.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    @Override
    public CategorieDTO saveCategorie(CategorieDTO dto) {
        Categorie categorie = categorieMapper.toEntity(dto);
        Categorie saved = categorieRepository.save(categorie);
        return categorieMapper.toDTO(saved);
    }

    @Override
    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll()
                .stream()
                .map(categorieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieDTO getCategorie(Long id) {
        return categorieRepository.findById(id)
                .map(categorieMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
