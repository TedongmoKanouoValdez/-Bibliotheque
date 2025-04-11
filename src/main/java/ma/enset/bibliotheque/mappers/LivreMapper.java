package ma.enset.bibliotheque.mappers;

import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.enums.EtatLivre;
import ma.enset.bibliotheque.enums.EtatLivre;

import org.springframework.stereotype.Component;

@Component
public class LivreMapper {

    public Livre toEntity(LivreDTO dto) {
        Livre livre = new Livre();
        livre.setTitre(dto.getTitre());
        livre.setIsbn(dto.getIsbn());
        livre.setDateAcquisition(java.time.LocalDate.parse(dto.getDateAcquisition()));
        livre.setEtatLivre(EtatLivre.valueOf(dto.getEtatLivre()));
        return livre;
    }

    public LivreDTO toDto(Livre livre) {
        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setIsbn(livre.getIsbn());
        dto.setEtatLivre(livre.getEtatLivre().name()); // convert enum to String
        dto.setDateAcquisition(livre.getDateAcquisition().toString());

        if (livre.getAuteur() != null) dto.setAuteurId(livre.getAuteur().getId());
        if (livre.getEditeur() != null) dto.setEditeurId(livre.getEditeur().getId());

        return dto;
    }
}
