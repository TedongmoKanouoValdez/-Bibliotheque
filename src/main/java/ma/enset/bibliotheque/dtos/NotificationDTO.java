package ma.enset.bibliotheque.dtos;

public class NotificationDTO {
    private Long id;
    private String message;
    private boolean estLue;
    private String dateEnvoi;
    private Long adherentId; // Référence vers l'Adhérent
    private Long livreId;
}
