package ma.enset.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bibliotheque.enums.StatusEmprunt;

import java.time.LocalDate;

@Data
@Entity @NoArgsConstructor
@AllArgsConstructor
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;

    private LocalDate dateRetourEffectif;
    private boolean enRetard;
    private Double penalite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmprunt statut;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

}
