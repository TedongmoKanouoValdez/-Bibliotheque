package ma.enset.bibliotheque.web;

import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.services.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @PostMapping
    public LivreDTO create(@RequestBody LivreDTO dto) {
        return livreService.createLivre(dto);
    }

    @PutMapping("/{id}")
    public LivreDTO update(@PathVariable Long id, @RequestBody LivreDTO dto) {
        return livreService.modifierLivre(id, dto);
    }

    @GetMapping
    public List<LivreDTO> getAll() {
        return livreService.getAllLivres();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        livreService.supprimerLivre(id);
    }
}
