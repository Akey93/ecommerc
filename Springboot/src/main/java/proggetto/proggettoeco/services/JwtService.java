package proggetto.proggettoeco.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import proggetto.proggettoeco.UTILITY.exceptions.UserDoesNotExistException;

@Service

public class JwtService {
    private static final String SECRET_KEY = "yhuOkqQrQOobo5aPxOQ6r6Ad6ghlXvt7G4Q4Y9aA9SCBGXw3ASz15NWULC2jzHg6";
    public String extractUserEmail(String token) {
        return extractClaim(token, Claims :: getSubject);
    }
    public String extractUserEmailByRequest(HttpServletRequest request)throws RuntimeException {
        String header = request.getHeader("Authorization");
        
        if(header !=null&& header.startsWith("Bearer ")){
            String token = header.substring(7);
            return extractClaim(token, Claims :: getSubject);
        }throw new UserDoesNotExistException();   
    }
    public <T> T extractClaim(String token, Function <Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails){
        return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserEmail(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    } 
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Claims extractAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    } 
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
