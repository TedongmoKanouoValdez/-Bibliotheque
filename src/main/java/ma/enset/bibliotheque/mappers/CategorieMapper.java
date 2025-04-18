package ma.enset.bibliotheque.mappers;

import ma.enset.bibliotheque.dtos.CategorieDTO;
import ma.enset.bibliotheque.entities.Categorie;
import org.springframework.stereotype.Component;

@Component
public class CategorieMapper {

    public CategorieDTO toDto(Categorie categorie) {
        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        dto.setLibelle(categorie.getLibelle());
        dto.setDescription(categorie.getDescription());
        return dto;
    }

    public Categorie toEntity(CategorieDTO dto) {
        Categorie categorie = new Categorie();
        categorie.setId(dto.getId());
        categorie.setLibelle(dto.getLibelle());
        categorie.setDescription(dto.getDescription());
        return categorie;
    }
}
