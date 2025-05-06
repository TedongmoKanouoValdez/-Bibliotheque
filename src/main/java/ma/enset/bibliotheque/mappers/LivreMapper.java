package ma.enset.bibliotheque.mappers;

import lombok.AllArgsConstructor;
import ma.enset.bibliotheque.dtos.AuteurDTO;
import ma.enset.bibliotheque.dtos.CategorieDTO;
import ma.enset.bibliotheque.dtos.EditeurDTO;
import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.enums.EtatLivre;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class LivreMapper {
    private final CategorieMapper categorieMapper;
    private final AuteurMapper auteurMapper;
    private final EditeurMapper editeurMapper;

    public LivreDTO toDto(Livre livre) {
        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setIsbn(livre.getIsbn());
        dto.setDateAcquisition(livre.getDateAcquisition().toString());
        dto.setEtatLivre(livre.getEtatLivre().name());

        if (livre.getAuteur() != null) {
            dto.setAuteurId(livre.getAuteur().getId());
            dto.setAuteur(auteurMapper.toDto(livre.getAuteur()));
        }

        if (livre.getEditeur() != null) {
            dto.setEditeurId(livre.getEditeur().getId());
            dto.setEditeur(editeurMapper.toDto(livre.getEditeur()));
        }

        if (livre.getCategories() != null) {
            List<CategorieDTO> categorieDTOS = livre.getCategories()
                    .stream()
                    .map(categorieMapper::toDto)
                    .collect(Collectors.toList());
            dto.setCategories(categorieDTOS);
        }

        return dto;
    }

    public Livre toEntity(LivreDTO dto) {
        Livre livre = new Livre();
        livre.setId(dto.getId());
        livre.setTitre(dto.getTitre());
        livre.setIsbn(dto.getIsbn());
        livre.setDateAcquisition(LocalDate.parse(dto.getDateAcquisition()));

        if (dto.getEtatLivre() != null) {
            try {
                livre.setEtatLivre(EtatLivre.valueOf(dto.getEtatLivre().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("État du livre invalide : " + dto.getEtatLivre());
            }
        }

        return livre;
    }

}
