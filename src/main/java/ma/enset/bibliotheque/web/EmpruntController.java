package ma.enset.bibliotheque.web;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.EmpruntDTO;
import ma.enset.bibliotheque.entities.Emprunt;
import ma.enset.bibliotheque.entities.Utilisateur;
import ma.enset.bibliotheque.enums.StatusEmprunt;
import ma.enset.bibliotheque.mappers.EmpruntMapper;
import ma.enset.bibliotheque.repositories.EmpruntRepository;
import ma.enset.bibliotheque.repositories.LivreRepository;
import ma.enset.bibliotheque.repositories.UtilisateurRepository;
import ma.enset.bibliotheque.services.EmpruntService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ma.enset.bibliotheque.entities.Livre;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprunts")
@RequiredArgsConstructor
public class EmpruntController {

    @PersistenceContext
    private EntityManager entityManager;
    private final EmpruntService empruntService;
    private final UtilisateurRepository utilisateurRepository;
    private final LivreRepository livreRepository;
    private final EmpruntRepository empruntRepository;
    private final EmpruntMapper empruntMapper;

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

    @GetMapping("/penalites/{utilisateurId}")
    public Double getPenaliteTotale(@PathVariable Long utilisateurId) {
        return empruntService.getPenaliteTotale(utilisateurId);
    }

    @PostMapping("/emprunts/test-retard")
    @Transactional
    public EmpruntDTO createTestEmpruntAvecRetard() {
        Utilisateur utilisateur = utilisateurRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Livre livre = livreRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        // Assurez-vous que l'entité livre est attachée à la session Hibernate
        if (livre.getId() != null) {
            livre = entityManager.merge(livre); // Fusionner l'entité détachée si nécessaire
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setDateEmprunt(LocalDate.now().minusDays(10));
        emprunt.setDateRetourPrevu(LocalDate.now().minusDays(5)); // 5 jours de retard
        emprunt.setStatut(StatusEmprunt.EN_COURS);
        emprunt.setLivre(livre);  // l'entité livre doit être attachée
        emprunt.setUtilisateur(utilisateur);

        empruntRepository.save(emprunt);

        // ⚠️ Appel du service pour enregistrer le retour, ce qui calcule la pénalité
        return empruntService.enregistrerRetour(emprunt.getId());
    }

}
