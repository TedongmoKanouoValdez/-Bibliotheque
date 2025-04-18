package ma.enset.bibliotheque.dtos;

import lombok.Data;

@Data
public class AuteurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String nationalite;
}