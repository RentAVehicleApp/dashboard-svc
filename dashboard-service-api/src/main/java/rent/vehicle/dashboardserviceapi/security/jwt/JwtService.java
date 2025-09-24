package rent.vehicle.dashboardserviceapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import rent.vehicle.security.JwtAuthenticationDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor

public class JwtService {
    private final JWTVerifier jwtVerifier;
    private final Algorithm algorithm;


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
            .sign(algorithm);
    }
    private String generateRefreshToken(String login){
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return JWT.create()
                .withSubject(login)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public boolean validateToken(String token){
        try{
            jwtVerifier.verify(token);
            return true;
        }catch(JWTVerificationException e){
            return false;
        }

    }

}
