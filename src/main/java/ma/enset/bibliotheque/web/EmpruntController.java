package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.EmpruntDTO;
import ma.enset.bibliotheque.services.EmpruntService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprunts")
@RequiredArgsConstructor
public class EmpruntController {

    private final EmpruntService empruntService;

    @PostMapping("/create")
    public ResponseEntity<EmpruntDTO> createEmprunt(@RequestBody EmpruntDTO empruntDTO) {
        EmpruntDTO savedEmprunt = empruntService.createEmprunt(empruntDTO);
        return new ResponseEntity<>(savedEmprunt, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/retour")
    public EmpruntDTO enregistrerRetour(@PathVariable Long id) {
        return empruntService.enregistrerRetour(id);
    }
    @GetMapping("/historique/{utilisateurId}")
    public ResponseEntity<List<EmpruntDTO>> getHistoriqueEmprunts(@PathVariable Long utilisateurId) {
        List<EmpruntDTO> emprunts = empruntService.getHistoriqueEmprunts(utilisateurId);
        return ResponseEntity.ok(emprunts);  // Retourne la liste des emprunts dans la réponse HTTP
    }

    @GetMapping("/historique")
    public ResponseEntity<List<EmpruntDTO>> getHistoriqueAllEmprunts() {
        List<EmpruntDTO> emprunts = empruntService.getAllHistoriqueEmprunts();
        return ResponseEntity.ok(emprunts);
    }


}
