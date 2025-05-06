package ma.enset.bibliotheque.mappers;

import ma.enset.bibliotheque.dtos.EmpruntDTO;
import ma.enset.bibliotheque.entities.Emprunt;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.entities.Utilisateur;
import ma.enset.bibliotheque.repositories.LivreRepository;
import ma.enset.bibliotheque.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpruntMapper {


    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public EmpruntDTO toDto(Emprunt emprunt) {
        EmpruntDTO empruntDTO = new EmpruntDTO();
        empruntDTO.setId(emprunt.getId());
        empruntDTO.setDateEmprunt(emprunt.getDateEmprunt());
        empruntDTO.setDateRetourPrevu(emprunt.getDateRetourPrevu());
        empruntDTO.setDateRetourEffectif(emprunt.getDateRetourEffectif());
        empruntDTO.setStatut(emprunt.getStatut());

        if(emprunt.getLivre() != null) {
            empruntDTO.setLivreId(emprunt.getLivre().getId());
        }

        if(emprunt.getUtilisateur() != null) {
            empruntDTO.setUtilisateurId(emprunt.getUtilisateur().getId());
        }
        return empruntDTO;
    }
    public Emprunt fromDTO(EmpruntDTO DTO) {
        Emprunt emprunt = new Emprunt();

        // On récupère les entités en fonction des IDs
        Livre livre = livreRepository.findById(DTO.getLivreId())
                .orElseThrow(() -> new RuntimeException("Livre id not found: " + DTO.getLivreId()));

        Utilisateur utilisateur = utilisateurRepository.findById(DTO.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur id not found: " + DTO.getUtilisateurId()));

        // On assigne les entités récupérées
        emprunt.setLivre(livre);
        emprunt.setUtilisateur(utilisateur);

        emprunt.setId(DTO.getId());
        emprunt.setDateEmprunt(DTO.getDateEmprunt());
        emprunt.setDateRetourPrevu(DTO.getDateRetourPrevu());
        emprunt.setDateRetourEffectif(DTO.getDateRetourEffectif());
        emprunt.setStatut(DTO.getStatut());
        return emprunt;
    }
}
