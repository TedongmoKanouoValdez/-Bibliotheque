package ma.enset.bibliotheque.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bibliothecaire extends Utilisateur {
    private String poste; // Ex: "Responsable des prêts"
}
