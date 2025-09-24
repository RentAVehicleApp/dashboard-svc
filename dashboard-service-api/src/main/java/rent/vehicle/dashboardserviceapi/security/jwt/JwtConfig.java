package rent.vehicle.dashboardserviceapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("19e4a6034b0a5c73caeb80d3a0d0aa6397728a9a9fffaa40d434630В9a9a523e75383a5a3")
    private String secret;

    @Bean
    public Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    @Bean
    public JWTVerifier jwtVerifier(Algorithm algorithm) {
        return JWT.require(algorithm)
                .withIssuer("your-app-name") // если используете issuer
                .build();
    }
}