package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.EmpruntDTO;
import ma.enset.bibliotheque.services.EmpruntService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
