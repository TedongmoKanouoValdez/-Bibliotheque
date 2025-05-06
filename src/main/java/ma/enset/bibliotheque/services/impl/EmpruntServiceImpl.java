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


    @Override
    public EmpruntDTO enregistrerRetour(Long empruntId) {
        Emprunt emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt id not found: " + empruntId));

        if(emprunt.getDateRetourEffectif() != null ){
            throw new RuntimeException("Le livre a dej) été retourné. ");
        }

        emprunt.setDateRetourEffectif(LocalDate.now());
        emprunt.setStatut(StatusEmprunt.RETOURNÉ);

        Emprunt updatedEmprunt = empruntRepository.save(emprunt);
        return empruntMapper.toDto(updatedEmprunt);
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
