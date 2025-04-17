package ma.enset.bibliotheque.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.bibliotheque.dtos.EditeurDTO;
import ma.enset.bibliotheque.entities.Editeur;
import ma.enset.bibliotheque.mappers.EditeurMapper;
import ma.enset.bibliotheque.repositories.EditeurRepository;
import ma.enset.bibliotheque.services.EditeurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditeurServiceImpl implements EditeurService {
    private final EditeurRepository editeurRepository;
    private final EditeurMapper editeurMapper;

    @Override
    public EditeurDTO createEditeur(EditeurDTO dto) {
        Editeur editeur = editeurMapper.toEntity(dto);
        return editeurMapper.toDto(editeurRepository.save(editeur));
    }

    @Override
    public EditeurDTO updateEditeur(Long id, EditeurDTO dto) {
        Editeur editeur = editeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Éditeur non trouvé"));
        editeur.setNom(dto.getNom());
        editeur.setPrenom(dto.getPrenom());
        editeur.setAdresse(dto.getAdresse());
        return editeurMapper.toDto(editeurRepository.save(editeur));
    }

    @Override
    public void deleteEditeur(Long id) {
        if (!editeurRepository.existsById(id))
            throw new RuntimeException("Éditeur non trouvé");
        editeurRepository.deleteById(id);
    }

    @Override
    public List<EditeurDTO> getAllEditeurs() {
        return editeurRepository.findAll().stream()
                .map(editeurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EditeurDTO getEditeurById(Long id) {
        return editeurRepository.findById(id)
                .map(editeurMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Éditeur non trouvé"));
    }
}
