package ma.enset.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.enset.bibliotheque.emuns.EtatLivre;

import java.time.LocalDate;
import java.util.*;

@Data // Génère getters/setters, toString(), equals(), hashCode()
@NoArgsConstructor // Constructeur sans arguments
@AllArgsConstructor // Constructeur avec tous les arguments
@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String isbn;
    private LocalDate dateAcquisition;

    @Enumerated(EnumType.STRING)
    private EtatLivre etatLivre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auteur_id", foreignKey = @ForeignKey(name = "FK_LIVRE_AUTEUR"))
    private Auteur auteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editeur_id", foreignKey = @ForeignKey(name = "FK_LIVRE_EDITEUR"))
    private Editeur editeur;

    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Emprunt> emprunts = new ArrayList<>(); // Initialisation de la liste

    @ManyToMany
    @JoinTable(
            name = "livre_categorie",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<Categorie> categories = new HashSet<>(); // Initialisation directe
}