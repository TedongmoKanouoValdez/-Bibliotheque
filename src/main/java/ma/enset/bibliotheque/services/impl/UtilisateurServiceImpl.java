package ma.enset.bibliotheque.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.UtilisateurDTO;
import ma.enset.bibliotheque.entities.Utilisateur;
import ma.enset.bibliotheque.mappers.UtilisateurMapper;
import ma.enset.bibliotheque.repositories.UtilisateurRepository;
import ma.enset.bibliotheque.services.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    @Override
    public UtilisateurDTO saveUtilisateur(UtilisateurDTO utilisateurDto) {
        Optional<Utilisateur> existingUser = utilisateurRepository.findFirstByEmail(utilisateurDto.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
        }

        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);
        Utilisateur utilisateurSaved = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDTO(utilisateurSaved);
    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDTO> getUtilisateursParRoles(List<String> roles) {
        // Récupère les utilisateurs ayant les rôles spécifiés
        List<Utilisateur> utilisateurs = utilisateurRepository.findByRoleIn(roles);

        // Utilise le mapper pour convertir les entités Utilisateur en UtilisateurDTO
        return utilisateurs.stream()
                .map(utilisateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurDTO deleteUtilisateur(Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            utilisateurRepository.deleteById(id);
            return utilisateurMapper.toDTO(utilisateur);
        }
        else{
            throw new RuntimeException("Un utilisateur avec cet id " + id + " n' existe pas.");
        }
    }



}
