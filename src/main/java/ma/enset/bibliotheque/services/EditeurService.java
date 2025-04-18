package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.EditeurDTO;

import java.util.List;

public interface EditeurService {
    EditeurDTO createEditeur(EditeurDTO dto);
    EditeurDTO updateEditeur(Long id, EditeurDTO dto);
    void deleteEditeur(Long id);
    List<EditeurDTO> getAllEditeurs();
    EditeurDTO getEditeurById(Long id);
}
