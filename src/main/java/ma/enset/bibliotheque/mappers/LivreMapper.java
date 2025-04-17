package ma.enset.bibliotheque.mappers;
import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.entities.Categorie;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.enums.EtatLivre;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LivreMapper {

    public LivreDTO toDto(Livre livre) {
        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setIsbn(livre.getIsbn());
        dto.setDateAcquisition(livre.getDateAcquisition().toString());
        dto.setEtatLivre(livre.getEtatLivre().name());

        if (livre.getAuteur() != null) dto.setAuteurId(livre.getAuteur().getId());
        if (livre.getEditeur() != null) dto.setEditeurId(livre.getEditeur().getId());

        if (livre.getCategories() != null) {
            Set<Long> ids = livre.getCategories().stream().map(Categorie::getId).collect(Collectors.toSet());
            dto.setCategorieIds(ids);
        }

        return dto;
    }

    public Livre toEntity(LivreDTO dto) {
        Livre livre = new Livre();
        livre.setId(dto.getId());
        livre.setTitre(dto.getTitre());
        livre.setIsbn(dto.getIsbn());
        livre.setDateAcquisition(LocalDate.parse(dto.getDateAcquisition()));
        livre.setEtatLivre(EtatLivre.valueOf(dto.getEtatLivre().toUpperCase()));
        // les relations seront liées dans le service
        return livre;
    }
}
