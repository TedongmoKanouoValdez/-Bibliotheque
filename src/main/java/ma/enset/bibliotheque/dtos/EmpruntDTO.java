package ma.enset.bibliotheque.dtos;

import lombok.Data;
import ma.enset.bibliotheque.enums.StatusEmprunt;

import java.time.LocalDate;

@Data
public class EmpruntDTO {
    private Long id;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;
    private LocalDate dateRetourEffectif;
    private StatusEmprunt statut;
    private Long livreId;
    private Long UtilisateurId;
}
