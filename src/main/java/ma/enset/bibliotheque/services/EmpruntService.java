package ma.enset.bibliotheque.services;

import ma.enset.bibliotheque.dtos.EmpruntDTO;

public interface EmpruntService {
    EmpruntDTO createEmprunt(EmpruntDTO empruntDTO);
}
