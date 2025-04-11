package ma.enset.bibliotheque.web;

import lombok.RequiredArgsConstructor;
import ma.enset.bibliotheque.dtos.LivreDTO;
import ma.enset.bibliotheque.services.LivreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/livres")
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;


    @PostMapping
    public LivreDTO createLivre(@RequestBody LivreDTO livreDTO) {
        return livreService.createLivre(livreDTO);
    }
}
