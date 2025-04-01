package ma.enset.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bibliotheque.emuns.StatusEmprunt;

import java.time.LocalDate;
import java.util.Date;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmprunt statut;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "adherent_id", nullable = false)
    private Adherent adherent;
}
