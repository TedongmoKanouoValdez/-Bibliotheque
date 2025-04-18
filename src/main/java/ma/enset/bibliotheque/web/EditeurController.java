package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.EditeurDTO;
import ma.enset.bibliotheque.services.EditeurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editeurs")
@RequiredArgsConstructor
public class EditeurController {
    private final EditeurService editeurService;

    @PostMapping
    public ResponseEntity<EditeurDTO> create(@RequestBody EditeurDTO dto) {
        return ResponseEntity.ok(editeurService.createEditeur(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditeurDTO> update(@PathVariable Long id, @RequestBody EditeurDTO dto) {
        return ResponseEntity.ok(editeurService.updateEditeur(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        editeurService.deleteEditeur(id);
        return ResponseEntity.ok("✅ Supprimé avec succès !");
    }

    @GetMapping
    public List<EditeurDTO> all() {
        return editeurService.getAllEditeurs();
    }

    @GetMapping("/{id}")
    public EditeurDTO getById(@PathVariable Long id) {
        return editeurService.getEditeurById(id);
    }
}
