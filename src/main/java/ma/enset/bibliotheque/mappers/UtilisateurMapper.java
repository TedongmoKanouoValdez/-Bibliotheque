package ma.enset.bibliotheque.mappers;

import ma.enset.bibliotheque.dtos.UtilisateurDTO;
import ma.enset.bibliotheque.entities.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public UtilisateurDTO toDTO(Utilisateur utilisateur) {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setEmail(utilisateur.getEmail());
        dto.setAdresse(utilisateur.getAdresse());
        dto.setTelephone(utilisateur.getTelephone());
        dto.setPassword(utilisateur.getPassword());
        dto.setRole(utilisateur.getRole());
        return dto;
    }

    public Utilisateur toEntity(UtilisateurDTO dto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setAdresse(dto.getAdresse());
        utilisateur.setTelephone(dto.getTelephone());
        utilisateur.setPassword(dto.getPassword());
        utilisateur.setRole(dto.getRole());  // Correctif ici, pas besoin de setRole sur dto
           return utilisateur;
    }
}

