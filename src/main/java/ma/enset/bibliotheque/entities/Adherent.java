package ma.enset.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity @NoArgsConstructor
@AllArgsConstructor
public class Adherent extends Utilisateur {

    private Date dateInscript;

    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Emprunt> emprunts = new ArrayList<>();
}
