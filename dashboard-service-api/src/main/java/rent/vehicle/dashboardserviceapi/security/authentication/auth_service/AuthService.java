package rent.vehicle.dashboardserviceapi.security.authentication.auth_service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import rent.vehicle.security.JwtAuthenticationDto;
import rent.vehicle.security.RefreshTokenDto;
import rent.vehicle.security.UserCredentialsDto;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.WorkerAuthDto;

public interface AuthService {
    Mono<ResponseWorkerDto> registerUser(CreateWorkerDto createWorkerDto);

    Mono<JwtAuthenticationDto> loginUser(UserCredentialsDto credentialsDto);

    Mono<JwtAuthenticationDto> refreshTokens(RefreshTokenDto refreshTokenDto);
}