package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.UtilisateurDTO;
import ma.enset.bibliotheque.entities.Utilisateur;
import ma.enset.bibliotheque.security.JwtUtil;
import ma.enset.bibliotheque.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final JwtUtil jwtUtil;

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

    @PutMapping("/{id}")
    public UtilisateurDTO updateUtilisateur (@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO) {
        return utilisateurService.updateUtilisateur(id, utilisateurDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UtilisateurDTO loginRequest) {
        UtilisateurDTO utilisateur = utilisateurService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (utilisateur != null) {
            // Générer un token JWT après la validation des identifiants
            String token = jwtUtil.generateToken(utilisateur.getEmail());

            // Retourner le token avec les informations de l'utilisateur
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("utilisateur", utilisateur);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Email ou mot de passe incorrect"));
        }
    }

}
