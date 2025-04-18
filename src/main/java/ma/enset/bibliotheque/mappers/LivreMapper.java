package ma.enset.bibliotheque.mappers;

import lombok.AllArgsConstructor;
import ma.enset.bibliotheque.dtos.CategorieDTO;
import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.entities.Auteur;
import ma.enset.bibliotheque.entities.Editeur;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.enums.EtatLivre;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// LivreMapper.java
@Component
@AllArgsConstructor
public class LivreMapper {
    private final CategorieMapper categorieMapper;

    public LivreDTO toDto(Livre livre) {
        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setIsbn(livre.getIsbn());
        dto.setDateAcquisition(livre.getDateAcquisition().toString());
        dto.setEtatLivre(livre.getEtatLivre().name());
        dto.setAuteurId(livre.getAuteur().getId());
        dto.setEditeurId(livre.getEditeur().getId());

        // Convertir les catégories
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
        livre.setEtatLivre(EtatLivre.valueOf(dto.getEtatLivre()));
        // ⚠️ Les relations (auteur, editeur, catégories) sont gérées dans le service
        return livre;
    }

}
