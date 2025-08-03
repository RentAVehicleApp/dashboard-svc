package rent.vehicle.dashboardserviceapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.query.KeysetScrollDelegate;
import org.springframework.stereotype.Component;
import rent.vehicle.security.JwtAuthenticationDto;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final JWTVerifier jwtVerifier;
    @Value("19e4a6034b0a5c73caeb80d3a0d0aa6397728a9a9fffaa40d434630В9a9a523e75383a5a3")
    private String jwtSecret;

    public JwtAuthenticationDto generateAuthToken(String login){
    JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
    jwtDto.setToken(generateJwtToken(login));
    jwtDto.setRefreshToken(generateRefreshToken(login));
    return jwtDto;
    }
    public JwtAuthenticationDto refreshBaseToken(String login,String refreshToken){
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(generateJwtToken(login));
        jwtDto.setRefreshToken(generateRefreshToken(login));
        return jwtDto;
    }
    public String getLoginFromToken(String token){
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String subject = decodedJWT.getSubject();
        if(subject != null&&!subject.isEmpty()){
            return subject;
        }
        return decodedJWT.getClaim("login").asString();
    }


    private String generateJwtToken(String login){
    Date date = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
    return JWT.create()
            .withSubject(login)
            .withExpiresAt(date)
            .sign(getSignInKey());
    }
    private String generateRefreshToken(String login){
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return JWT.create()
                .withSubject(login)
                .withExpiresAt(date)
                .sign(getSignInKey());
    }


    private Algorithm getSignInKey(){
    byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
        return Algorithm.HMAC256(keyBytes);
    }
}
