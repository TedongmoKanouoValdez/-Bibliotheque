package ma.enset.bibliotheque.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.AuteurDTO;
import ma.enset.bibliotheque.entities.Auteur;
import ma.enset.bibliotheque.mappers.AuteurMapper;
import ma.enset.bibliotheque.repositories.AuteurRepository;
import ma.enset.bibliotheque.services.AuteurService;
import org.springframework.stereotype.Service;
import ma.enset.bibliotheque.services.AuteurService;


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
}
