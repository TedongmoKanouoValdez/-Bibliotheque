package ma.enset.bibliotheque.mappers;

import ma.enset.bibliotheque.dtos.EditeurDTO;
import ma.enset.bibliotheque.entities.Editeur;
import org.springframework.stereotype.Component;

@Component
public class EditeurMapper {
    public EditeurDTO toDto(Editeur editeur) {
        EditeurDTO dto = new EditeurDTO();
        dto.setId(editeur.getId());
        dto.setNom(editeur.getNom());
        dto.setPrenom(editeur.getPrenom());
        dto.setAdresse(editeur.getAdresse());
        return dto;
    }

    public Editeur toEntity(EditeurDTO dto) {
        Editeur editeur = new Editeur();
        editeur.setNom(dto.getNom());
        editeur.setPrenom(dto.getPrenom());
        editeur.setAdresse(dto.getAdresse());
        return editeur;
    }
}
