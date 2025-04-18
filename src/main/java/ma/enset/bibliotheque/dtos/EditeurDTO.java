package ma.enset.bibliotheque.dtos;

import lombok.Data;

@Data
public class EditeurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
}
