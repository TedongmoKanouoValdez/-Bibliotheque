package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.UtilisateurDTO;
import ma.enset.bibliotheque.entities.Utilisateur;
import ma.enset.bibliotheque.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping("/create")
    public UtilisateurDTO saveUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        return utilisateurService.saveUtilisateur(utilisateurDTO);
    }

    @GetMapping("/all")
    public List<UtilisateurDTO> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/roles")
    public List<UtilisateurDTO> getUtilisateursParRoles(@RequestParam List<String> roles) {
        return utilisateurService.getUtilisateursParRoles(roles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> deleteUtilisateur(@PathVariable Long id)  {
        UtilisateurDTO utilisateurDTO = utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok(utilisateurDTO);
    }
}
