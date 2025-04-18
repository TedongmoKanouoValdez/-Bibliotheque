package ma.enset.bibliotheque.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.entities.Auteur;
import ma.enset.bibliotheque.entities.Categorie;
import ma.enset.bibliotheque.entities.Editeur;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.mappers.LivreMapper;
import ma.enset.bibliotheque.repositories.AuteurRepository;
import ma.enset.bibliotheque.repositories.CategorieRepository;
import ma.enset.bibliotheque.repositories.EditeurRepository;
import ma.enset.bibliotheque.repositories.LivreRepository;
import ma.enset.bibliotheque.services.LivreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LivreServiceImpl implements LivreService {

    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;
    private final EditeurRepository editeurRepository;
    private final LivreMapper livreMapper;
    private final CategorieRepository categorieRepository; // 👈 à ajouter ici

    @Override
    @Transactional
    public LivreDTO createLivre(LivreDTO dto) {
        Livre livre = livreMapper.toEntity(dto);

        if (dto.getAuteurId() != null) {
            auteurRepository.findById(dto.getAuteurId()).ifPresent(livre::setAuteur);
        }

        if (dto.getEditeurId() != null) {
            editeurRepository.findById(dto.getEditeurId()).ifPresent(livre::setEditeur);
        }

        Livre saved = livreRepository.save(livre);
        return livreMapper.toDto(saved);
    }

    @Override
    public LivreDTO getLivreById(Long id) {
        return livreRepository.findById(id)
                .map(livreMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));
    }
    @Override
    @Transactional
    public List<LivreDTO> getAllLivres() {
        return livreRepository.findAllWithCategories()
                .stream()
                .map(livreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LivreDTO updateLivre(Long id, LivreDTO dto) {
        Livre existing = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        existing.setTitre(dto.getTitre());
        existing.setIsbn(dto.getIsbn());
        existing.setDateAcquisition(java.time.LocalDate.parse(dto.getDateAcquisition()));
        existing.setEtatLivre(ma.enset.bibliotheque.enums.EtatLivre.valueOf(dto.getEtatLivre()));

        if (dto.getAuteurId() != null) {
            auteurRepository.findById(dto.getAuteurId()).ifPresent(existing::setAuteur);
        }

        if (dto.getEditeurId() != null) {
            editeurRepository.findById(dto.getEditeurId()).ifPresent(existing::setEditeur);
        }

        Livre updated = livreRepository.save(existing);
        return livreMapper.toDto(updated);
    }

    @Override
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void addCategorieToLivre(Long livreId, Long categorieId) {
        Livre livre = livreRepository.findById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        livre.getCategories().add(categorie);
        livreRepository.save(livre);
    }

}
