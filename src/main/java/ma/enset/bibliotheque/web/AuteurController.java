package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.AuteurDTO;
import ma.enset.bibliotheque.services.AuteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auteurs")
@RequiredArgsConstructor
public class AuteurController {

    private final AuteurService auteurService;

    @PostMapping
    public AuteurDTO createAuteur(@RequestBody AuteurDTO auteurDTO) {
        return auteurService.createAuteur(auteurDTO);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> modifierAuteur(@PathVariable Long id, @RequestBody AuteurDTO auteurDTO) {
        try {
            // Appel du service pour modifier l'auteur
            AuteurDTO updatedAuteur = auteurService.modifierAuteur(id, auteurDTO);
            return ResponseEntity.ok(updatedAuteur);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Auteur non trouvé");
        }
    }

    // Lister tous les auteurs
    @GetMapping
    public List<AuteurDTO> getAllAuteurs() {
        return auteurService.getAllAuteurs();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerAuteur(@PathVariable Long id) {
        try {
            auteurService.supprimerAuteur(id);
            return ResponseEntity.ok("Auteur supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Auteur non trouvé");
        }
    }

}

