package ma.enset.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.Data;
import ma.enset.bibliotheque.emuns.Role;

@Data
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    private String telephone;
    private String adresse;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
