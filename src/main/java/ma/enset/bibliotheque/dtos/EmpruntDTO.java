package ma.enset.bibliotheque.dtos;

public class EmpruntDTO {
    private Long id;
    private String dateEmprunt;
    private String dateRetourPrevu;
    private String dateRetourEffectif;
    private String statut;
    private Long adherentId;
    private Long livreId;
}
