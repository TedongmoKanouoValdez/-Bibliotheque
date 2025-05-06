package ma.enset.bibliotheque.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.EmpruntDTO;
import ma.enset.bibliotheque.entities.Emprunt;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.entities.Utilisateur;
import ma.enset.bibliotheque.enums.StatusEmprunt;
import ma.enset.bibliotheque.mappers.EmpruntMapper;
import ma.enset.bibliotheque.repositories.EmpruntRepository;
import ma.enset.bibliotheque.repositories.LivreRepository;
import ma.enset.bibliotheque.repositories.UtilisateurRepository;
import ma.enset.bibliotheque.services.EmpruntService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpruntServiceImpl implements EmpruntService {
    @PersistenceContext
    private EntityManager entityManager;
    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final EmpruntMapper empruntMapper;


    @Override
    @Transactional
    public EmpruntDTO createEmprunt(EmpruntDTO empruntDTO) {
        Livre livre = livreRepository.findById(empruntDTO.getLivreId())
                .orElseThrow(() -> new RuntimeException("Livre id not found: " + empruntDTO.getLivreId()));

        Utilisateur utilisateur = utilisateurRepository.findById(empruntDTO.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur id not found: " + empruntDTO.getUtilisateurId()));

        Emprunt emprunt = empruntMapper.fromDTO(empruntDTO);
        emprunt.setLivre(livre);
        emprunt.setUtilisateur(utilisateur);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevu(LocalDate.now().plusDays(15));
        emprunt.setStatut(StatusEmprunt.EN_COURS);

        // Fusionner l'entité avec l'EntityManager pour éviter les problèmes de détachement
        Emprunt mergedEmprunt = entityManager.merge(emprunt);

        return empruntMapper.toDto(mergedEmprunt);
    }


    public EmpruntDTO enregistrerRetour(Long empruntId) {
        Emprunt emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        LocalDate today = LocalDate.now();
        emprunt.setDateRetourEffectif(today);

        if (emprunt.getDateRetourPrevu() != null && today.isAfter(emprunt.getDateRetourPrevu())) {
            long joursDeRetard = ChronoUnit.DAYS.between(emprunt.getDateRetourPrevu(), today);
            emprunt.setEnRetard(true);
            emprunt.setPenalite(joursDeRetard * 1.5); // Exemple : 1,5€ par jour de retard
        } else {
            emprunt.setEnRetard(false);
            emprunt.setPenalite(0.0);
        }

        empruntRepository.save(emprunt);
        return empruntMapper.toDto(emprunt);
    }

    public Double getPenaliteTotale(Long utilisateurId) {
        List<Emprunt> emprunts = empruntRepository.findByUtilisateurId(utilisateurId);
        return emprunts.stream()
                .filter(Emprunt::isEnRetard) // car c'est un boolean
                .mapToDouble(e -> e.getPenalite() != null ? e.getPenalite() : 0.0)
                .sum();
    }



    @Override
    public List<EmpruntDTO> getHistoriqueEmprunts(Long utilisateurId) {
        // on recupere la liste de tous les emprunts de l'utilisateur
        List<Emprunt> emprunts = empruntRepository.findByUtilisateurIdOrderByDateEmpruntDesc(utilisateurId);
           // convertir les entitées en DTO
            return emprunts.stream().map(empruntMapper::toDto).toList();
    }

    @Override
    public List<EmpruntDTO> getAllHistoriqueEmprunts() {
        return empruntRepository.findAll()
                .stream()
                .map(empruntMapper::toDto)
                .collect(Collectors.toList());
    }
}
