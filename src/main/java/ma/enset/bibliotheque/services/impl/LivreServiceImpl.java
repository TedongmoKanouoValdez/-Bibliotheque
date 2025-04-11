// ma/enset/bibliotheque/services/impl/LivreServiceImpl.java
package ma.enset.bibliotheque.services.impl;

import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.entities.Livre;
import ma.enset.bibliotheque.mappers.LivreMapper;
import ma.enset.bibliotheque.repositories.LivreRepository;
import ma.enset.bibliotheque.services.LivreService;
import org.springframework.stereotype.Service;

@Service
public class LivreServiceImpl implements LivreService {

    private final LivreRepository livreRepository;
    private final LivreMapper livreMapper;

    public LivreServiceImpl(LivreRepository livreRepository, LivreMapper livreMapper) {
        this.livreRepository = livreRepository;
        this.livreMapper = livreMapper;
    }

    @Override
    public LivreDTO createLivre(LivreDTO livreDTO) {
        // Conversion du DTO en entité
        Livre livre = livreMapper.toEntity(livreDTO);

        // Sauvegarde dans la base de données
        livre = livreRepository.save(livre);

        // Conversion de l'entité en DTO pour renvoyer
        return livreMapper.toDto(livre);
    }
}
