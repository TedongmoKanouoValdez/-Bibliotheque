package ma.enset.bibliotheque.mappers;

import ma.enset.bibliotheque.dtos.CategorieDTO;
import ma.enset.bibliotheque.entities.Categorie;
import org.springframework.stereotype.Component;

@Component
public class CategorieMapper {
    public CategorieDTO toDTO(Categorie entity) {
        CategorieDTO dto = new CategorieDTO();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Categorie toEntity(CategorieDTO dto) {
        Categorie entity = new Categorie();
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
