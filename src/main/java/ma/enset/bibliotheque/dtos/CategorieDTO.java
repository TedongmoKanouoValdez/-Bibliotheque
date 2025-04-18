package ma.enset.bibliotheque.dtos;

import lombok.Data;

@Data
public class CategorieDTO {
    private Long id;
    private String libelle;
    private String description;
}
