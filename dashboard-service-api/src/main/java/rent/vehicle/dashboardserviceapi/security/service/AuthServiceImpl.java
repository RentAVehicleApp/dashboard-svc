package rent.vehicle.dashboardserviceapi.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rent.vehicle.dashboardserviceapi.security.CustomUserDetails;
import rent.vehicle.dashboardserviceapi.security.dto.LoginRequestDto;
import rent.vehicle.dashboardserviceapi.security.dto.token.RefreshTokenResponseDto;
import rent.vehicle.dashboardserviceapi.security.dto.token.TokenResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<TokenResponseDto> loginCustomer(LoginRequestDto loginRequestDto) {
        try {
            String email = loginRequestDto.getEmail();
            String password = loginRequestDto.getPassword();

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);

            TokenResponseDto response = TokenResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(null); // или создать error response DTO
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

    @Override
    public ResponseEntity<RefreshTokenResponseDto> refreshCustomerAccessToken(String refreshToken) {
        try {
            // Валидируем refresh token
            if (!jwtService.validateToken(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            // Проверяем, что токен не истек
            if (jwtService.isTokenExpired(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            // Извлекаем username из refresh token
            String username = jwtService.extractUsername(refreshToken);

            // Загружаем пользователя
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Генерируем новый access token
            String newAccessToken = jwtService.generateAccessToken(userDetails);

            // Создаем ответ
            RefreshTokenResponseDto response = RefreshTokenResponseDto.builder()
                    .accessToken(newAccessToken)
                    .build();

            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            log.error("User not found for refresh token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            log.error("Error refreshing access token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> logoutCustomer(UUID id) {
        return null;
    }
}
