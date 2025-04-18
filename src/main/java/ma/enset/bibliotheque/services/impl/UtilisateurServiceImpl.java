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


}
