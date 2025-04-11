package ma.enset.bibliotheque.mappers;

import ma.enset.bibliotheque.dtos.AuteurDTO;
import ma.enset.bibliotheque.entities.Auteur;
import org.springframework.stereotype.Component;

@Component
public class AuteurMapper {

    public Auteur toEntity(AuteurDTO dto) {
        Auteur auteur = new Auteur();
        auteur.setNom(dto.getNom());
        auteur.setPrenom(dto.getPrenom());
        auteur.setNationalite(dto.getNationalite());
        return auteur;
    }

    public AuteurDTO toDto(Auteur auteur) {
        AuteurDTO dto = new AuteurDTO();
        dto.setId(auteur.getId());
        dto.setNom(auteur.getNom());
        dto.setPrenom(auteur.getPrenom());
        dto.setNationalite(auteur.getNationalite());
        return dto;
    }
}
