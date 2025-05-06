package ma.enset.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bibliotheque.entities.Auteur;
import ma.enset.bibliotheque.entities.Categorie;
import ma.enset.bibliotheque.entities.Editeur;
import ma.enset.bibliotheque.enums.EtatLivre;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String isbn;
    private LocalDate dateAcquisition;

    @Enumerated(EnumType.STRING)
    private EtatLivre etatLivre;


    @ManyToOne
    private Auteur auteur;

    @ManyToOne
    private Editeur editeur;

    @ManyToMany
    private Set<Categorie> categories = new HashSet<>();
}
