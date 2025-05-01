package ma.enset.bibliotheque.services.impl;

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

@Service
@RequiredArgsConstructor
public class EmpruntServiceImpl implements EmpruntService {
    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final EmpruntMapper empruntMapper;

    @Override
    public EmpruntDTO createEmprunt(EmpruntDTO empruntDTO) {
        Livre livre = livreRepository.findById(empruntDTO.getLivreId())
                .orElseThrow(() -> new RuntimeException("Livre id not found" + empruntDTO.getLivreId()));

        Utilisateur utilisateur = utilisateurRepository.findById(empruntDTO.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur id not found: " + empruntDTO.getUtilisateurId()));

        Emprunt emprunt = empruntMapper.fromDTO(empruntDTO);
        emprunt.setLivre(livre);
        emprunt.setUtilisateur(utilisateur);
        emprunt.setDateEmprunt(LocalDate.now());
        // Le calcul de la date de retour prévue (15 jours après la date d'emprunt)
        emprunt.setDateRetourPrevu(LocalDate.now().plusDays(15));
        emprunt.setStatut(StatusEmprunt.EN_COURS);

        Emprunt saved = empruntRepository.save(emprunt);
        return empruntMapper.toDto(saved);
    }
}
