package rent.vehicle.dashboardserviceapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import rent.vehicle.security.JwtAuthenticationDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JWTVerifier jwtVerifier;
    private final Algorithm algorithm;


    public JwtAuthenticationDto generateAuthToken(String login,Set<String> roles){
    JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
    jwtDto.setToken(generateJwtToken(login,roles));
    jwtDto.setRefreshToken(generateRefreshToken(login,roles));
    return jwtDto;
    }
    public JwtAuthenticationDto refreshBaseToken(String login, String refreshToken, Set<String> roles) {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();

        // Генерируем НОВЫЙ access token
        jwtDto.setToken(generateJwtToken(login, roles));

        // Возвращаем ТОТ ЖЕ refresh token (или генерируем новый по вашей логике)
        jwtDto.setRefreshToken(refreshToken);  // используем существующий

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
    public Set<String> getRolesFromToken(String token) {
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            // JWT хранит роли как List, не Set
            List<String> rolesList = decodedJWT.getClaim("roles").asList(String.class);

            // Проверка на null и преобразование в Set
            if (rolesList != null && !rolesList.isEmpty()) {
                return new HashSet<>(rolesList);
            }

            // Возвращаем дефолтную роль если roles отсутствуют
            return Set.of("USER");

        } catch (Exception e) {
            // Если ошибка при извлечении - возвращаем дефолтную роль
            return Set.of("USER");
        }
    }


    private String generateJwtToken(String login,Set<String> roles){
    Date date = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
    return JWT.create()
            .withSubject(login)
            .withClaim("roles", new ArrayList<>(roles))
            .withExpiresAt(date)
            .sign(algorithm);
    }
    private String generateRefreshToken(String login,Set<String> roles){
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return JWT.create()
                .withSubject(login)
                .withClaim("roles", new ArrayList<>(roles))
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public boolean validateToken(String token){
            try {
                DecodedJWT decodedJWT = jwtVerifier.verify(token);

                // Добавьте логирование
                log.info("Token subject: {}", decodedJWT.getSubject());
                log.info("Token expires at: {}", decodedJWT.getExpiresAt());
                log.info("Current time: {}", new Date());

                return true;
            } catch (TokenExpiredException e) {
                log.error("Token expired: {}", e.getMessage());
                return false;
            } catch (JWTVerificationException e) {
                log.error("Token verification failed: {}", e.getMessage());
                return false;
            }
        }

    }


