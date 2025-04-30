package ma.enset.bibliotheque.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // Clé secrète pour la signature du token (à sécuriser dans un fichier de configuration)
    private final String SECRET_KEY = "secret_key";

    // Méthode pour générer un token JWT
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)  // le subject du token est l'email de l'utilisateur
                .setIssuedAt(new Date())  // date d'émission du token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 heures d'expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // signature avec la clé secrète
                .compact();  // compacter et créer le token
    }

    // Méthode pour extraire l'email du token
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // extraction du subject (email)
    }

    // Méthode pour vérifier si un token est valide
    public boolean isTokenValid(String token, String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    // Méthode pour vérifier si le token est expiré
    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());  // vérifier si la date d'expiration est avant maintenant
    }
}
