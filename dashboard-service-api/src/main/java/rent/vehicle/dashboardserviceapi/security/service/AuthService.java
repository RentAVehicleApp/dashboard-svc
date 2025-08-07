package rent.vehicle.dashboardserviceapi.security.service;

import org.springframework.http.ResponseEntity;
import rent.vehicle.dashboardserviceapi.security.dto.LoginRequestDto;
import rent.vehicle.dashboardserviceapi.security.dto.token.RefreshTokenResponseDto;
import rent.vehicle.dashboardserviceapi.security.dto.token.TokenResponseDto;

import java.util.UUID;

public interface AuthService {
    ResponseEntity<TokenResponseDto> loginCustomer(LoginRequestDto loginRequestDto);

    ResponseEntity<RefreshTokenResponseDto> refreshCustomerAccessToken(String refreshToken);

    ResponseEntity<String> logoutCustomer(UUID id);
}
