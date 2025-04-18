package ma.enset.bibliotheque.dtos;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class LivreDTO {
    private Long id;
    private String titre;
    private String isbn;
    private String dateAcquisition;
    private String etatLivre;
    private Long auteurId;
    private AuteurDTO auteur;
    private EditeurDTO editeur;
    private Long editeurId;
    private List<CategorieDTO> categories; // ✅ Ajouté
}
