package ma.enset.bibliotheque.services.impl;

import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.entities.Auteur;
import ma.enset.bibliotheque.entities.Categorie;
import ma.enset.bibliotheque.entities.Editeur;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.enums.EtatLivre;
import ma.enset.bibliotheque.mappers.LivreMapper;
import ma.enset.bibliotheque.repositories.AuteurRepository;
import ma.enset.bibliotheque.repositories.CategorieRepository;
import ma.enset.bibliotheque.repositories.EditeurRepository;
import ma.enset.bibliotheque.repositories.LivreRepository;
import ma.enset.bibliotheque.services.LivreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivreServiceImpl implements LivreService {

    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;
    private final EditeurRepository editeurRepository;
    private final CategorieRepository categorieRepository;
    private final LivreMapper livreMapper;

    public LivreServiceImpl(LivreRepository livreRepository,
                            AuteurRepository auteurRepository,
                            EditeurRepository editeurRepository,
                            CategorieRepository categorieRepository,
                            LivreMapper livreMapper) {
        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
        this.editeurRepository = editeurRepository;
        this.categorieRepository = categorieRepository;
        this.livreMapper = livreMapper;
    }

    @Override
    public LivreDTO createLivre(LivreDTO livreDTO) {
        try {
            Livre livre = livreMapper.toEntity(livreDTO);

            // Lier Auteur
            if (livreDTO.getAuteurId() != null) {
                Auteur auteur = auteurRepository.findById(livreDTO.getAuteurId())
                        .orElseThrow(() -> new RuntimeException("Auteur introuvable avec ID : " + livreDTO.getAuteurId()));
                livre.setAuteur(auteur);
            }

            // Lier Editeur
            if (livreDTO.getEditeurId() != null) {
                Editeur editeur = editeurRepository.findById(livreDTO.getEditeurId())
                        .orElseThrow(() -> new RuntimeException("Éditeur introuvable avec ID : " + livreDTO.getEditeurId()));
                livre.setEditeur(editeur);
            }

            // Lier Catégories
            if (livreDTO.getCategorieIds() != null && !livreDTO.getCategorieIds().isEmpty()) {
                Set<Categorie> categories = new LinkedHashSet<>();
                for (Long catId : livreDTO.getCategorieIds()) {
                    Categorie categorie = categorieRepository.findById(catId)
                            .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec ID : " + catId));
                    categories.add(categorie);
                }
                livre.setCategories(categories);
            }

            // Sauvegarder
            Livre savedLivre = livreRepository.save(livre);
            return livreMapper.toDto(savedLivre);

        } catch (Exception e) {
            e.printStackTrace(); // pour le debug
            throw new RuntimeException("Erreur lors de la création du livre : " + e.getMessage());
        }
    }

    @Override
    public LivreDTO modifierLivre(Long id, LivreDTO dto) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        livre.setTitre(dto.getTitre());
        livre.setIsbn(dto.getIsbn());
        livre.setDateAcquisition(LocalDate.parse(dto.getDateAcquisition()));
        livre.setEtatLivre(EtatLivre.valueOf(dto.getEtatLivre().toUpperCase()));

        if (dto.getAuteurId() != null) {
            livre.setAuteur(auteurRepository.findById(dto.getAuteurId())
                    .orElseThrow(() -> new RuntimeException("Auteur non trouvé")));
        }

        if (dto.getEditeurId() != null) {
            livre.setEditeur(editeurRepository.findById(dto.getEditeurId())
                    .orElseThrow(() -> new RuntimeException("Éditeur non trouvé")));
        }

        if (dto.getCategorieIds() != null) {
            Set<Categorie> categories = dto.getCategorieIds().stream()
                    .map(idCat -> categorieRepository.findById(idCat)
                            .orElseThrow(() -> new RuntimeException("Catégorie ID " + idCat + " non trouvée")))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            livre.setCategories(categories);
        }

        Livre updated = livreRepository.save(livre);
        return livreMapper.toDto(updated);
    }

    @Override
    public List<LivreDTO> getAllLivres() {
        return livreRepository.findAll().stream()
                .map(livreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void supprimerLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
