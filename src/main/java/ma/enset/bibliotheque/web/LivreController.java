package ma.enset.bibliotheque.web;

import lombok.AllArgsConstructor;
import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.services.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
@AllArgsConstructor
public class LivreController {

    private final LivreService livreService;

    @PostMapping
    public LivreDTO create(@RequestBody LivreDTO dto) {
        return livreService.createLivre(dto);
    }

    @GetMapping("/{id}")
    public LivreDTO getById(@PathVariable Long id) {
        return livreService.getLivreById(id);
    }

    @GetMapping
    public List<LivreDTO> getAll() {
        return livreService.getAllLivres();
    }

    @PutMapping("/{id}")
    public LivreDTO update(@PathVariable Long id, @RequestBody LivreDTO dto) {
        return livreService.updateLivre(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        livreService.deleteLivre(id);
    }

    @PutMapping("/{livreId}/categories/{categorieId}")
    public void addCategorieToLivre(@PathVariable Long livreId, @PathVariable Long categorieId) {
        livreService.addCategorieToLivre(livreId, categorieId);
    }

}
