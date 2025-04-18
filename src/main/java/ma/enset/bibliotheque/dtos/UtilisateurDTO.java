package ma.enset.bibliotheque.dtos;

import lombok.Data;
import ma.enset.bibliotheque.enums.Role;

@Data
public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String password;
    private Role role;
}
