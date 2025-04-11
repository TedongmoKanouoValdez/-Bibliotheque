package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.AuteurDTO;
import ma.enset.bibliotheque.services.AuteurService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auteurs")
@RequiredArgsConstructor
public class AuteurController {

    private final AuteurService auteurService;

    @PostMapping
    public AuteurDTO createAuteur(@RequestBody AuteurDTO auteurDTO) {
        return auteurService.createAuteur(auteurDTO);
    }
}
