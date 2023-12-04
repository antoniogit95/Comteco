package comteco.backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import comteco.backend.user.Role;
import comteco.backend.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Servicio para la gestión de tokens JWT.
 */
@Service
public class JwtService {

    private static final String SECRET_KEY="gflIYt8y9eWGqNgOtd0GDSW4SV0rzonGqPCz7kEsXuE";

    /**
     * Genera un token JWT para el usuario proporcionado.
     *
     * @param user Los detalles del usuario para los que se genera el token.
     * @return Un token JWT generado.
     */
    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        Role role = ((User) user).getRole();
        System.out.println(role);
        return Jwts.builder()
                .setClaims(extraClaims)
                .claim("role", role)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey getSecretKey(){
        byte[] keyBytes = SECRET_KEY.getBytes();
        return new SecretKeySpec(keyBytes, "HmacSHA256"); 
    }

    /**
     * Obtiene el nombre de usuario a partir de un token JWT.
     *
     * @param token El token JWT del que se extrae el nombre de usuario.
     * @return El nombre de usuario extraído del token.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Verifica si un token JWT es válido para los detalles del usuario proporcionados.
     *
     * @param token El token JWT que se verifica.
     * @param userDetails Los detalles del usuario con los que se compara el token.
     * @return `true` si el token es válido, de lo contrario, `false`.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username  = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration (String token){
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
