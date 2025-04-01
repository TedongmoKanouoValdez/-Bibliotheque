package ma.enset.bibliotheque.dtos;

import java.util.HashSet;
import java.util.Set;

public class LivreDTO {
    private Long id;
    private String titre;
    private String isbn;
    private String dateAcquisition;
    private String etatLivre;

    private Long auteurId;
    private Long editeurId;
    private Set<Long> categorieIds = new HashSet<>();

    private Set<EmpruntDTO> emprunts = new HashSet<>(); // Liste des emprunts détaillés sous forme de DTO
}
