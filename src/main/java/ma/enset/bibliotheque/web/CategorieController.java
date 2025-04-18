package ma.enset.bibliotheque.web;

import lombok.AllArgsConstructor;
import ma.enset.bibliotheque.dtos.CategorieDTO;
import ma.enset.bibliotheque.services.CategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    // ✅ Ajouter une catégorie
    @PostMapping
    public CategorieDTO create(@RequestBody CategorieDTO dto) {
        return categorieService.createCategorie(dto);
    }

    // ✅ Obtenir toutes les catégories
    @GetMapping
    public List<CategorieDTO> getAll() {
        return categorieService.getAllCategories();
    }

    // ✅ Obtenir une catégorie par ID
    @GetMapping("/{id}")
    public CategorieDTO getById(@PathVariable Long id) {
        return categorieService.getCategorieById(id);
    }

    // ✅ Modifier une catégorie
    @PutMapping("/{id}")
    public CategorieDTO update(@PathVariable Long id, @RequestBody CategorieDTO dto) {
        return categorieService.updateCategorie(id, dto);
    }

    // ✅ Supprimer une catégorie
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}
