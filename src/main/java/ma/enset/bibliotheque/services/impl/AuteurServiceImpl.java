package ma.enset.bibliotheque.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.AuteurDTO;
import ma.enset.bibliotheque.entities.Auteur;
import ma.enset.bibliotheque.mappers.AuteurMapper;
import ma.enset.bibliotheque.repositories.AuteurRepository;
import ma.enset.bibliotheque.services.AuteurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuteurServiceImpl implements AuteurService {

    private final AuteurRepository auteurRepository;
    private final AuteurMapper auteurMapper;

    @Override
    public AuteurDTO createAuteur(AuteurDTO auteurDTO) {
        Auteur auteur = auteurMapper.toEntity(auteurDTO);
        Auteur saved = auteurRepository.save(auteur);
        return auteurMapper.toDto(saved);
    }

    @Override
    public AuteurDTO modifierAuteur(Long id, AuteurDTO auteurDTO) {
        // Vérifie si l'auteur existe
        Optional<Auteur> auteurOptional = auteurRepository.findById(id);
        if (auteurOptional.isPresent()) {
            Auteur auteur = auteurOptional.get();
            // Met à jour les informations de l'auteur à partir du DTO
            auteur.setNom(auteurDTO.getNom());
            auteur.setPrenom(auteurDTO.getPrenom());
            auteur.setNationalite(auteurDTO.getNationalite());
            // Enregistre les changements
            Auteur updatedAuteur = auteurRepository.save(auteur);
            return auteurMapper.toDto(updatedAuteur); // Retourne le DTO de l'auteur mis à jour
        } else {
            // Si l'auteur n'existe pas, tu peux lancer une exception ou gérer l'erreur autrement
            throw new RuntimeException("Auteur non trouvé");
        }
    }

    @Override
    public List<AuteurDTO> getAllAuteurs() {
        List<Auteur> auteurs = auteurRepository.findAll();  // Récupérer tous les auteurs depuis la base de données
        return auteurs.stream()
                .map(auteurMapper::toDto)  // Convertir chaque auteur en AuteurDTO
                .collect(Collectors.toList());  // Retourner la liste de DTOs
    }

    @Override
    public void supprimerAuteur(Long id) {
        if (auteurRepository.existsById(id)) {
            auteurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Auteur non trouvé");
        }
    }


}
