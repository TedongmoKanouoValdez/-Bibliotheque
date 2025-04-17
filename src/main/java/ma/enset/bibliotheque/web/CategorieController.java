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

    @PostMapping
    public CategorieDTO create(@RequestBody CategorieDTO dto) {
        return categorieService.saveCategorie(dto);
    }

    @GetMapping
    public List<CategorieDTO> getAll() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategorieDTO getById(@PathVariable Long id) {
        return categorieService.getCategorie(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}
