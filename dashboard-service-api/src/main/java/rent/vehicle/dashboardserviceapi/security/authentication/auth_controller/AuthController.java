package rent.vehicle.dashboardserviceapi.security.authentication.auth_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.security.authentication.auth_service.AuthServiceImpl;
import rent.vehicle.dashboardserviceapi.security.jwt.JwtService;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardServiceImpl;
import rent.vehicle.security.JwtAuthenticationDto;
import rent.vehicle.security.RefreshTokenDto;
import rent.vehicle.security.UserCredentialsDto;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.WorkerAuthDto;

import java.security.Principal;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceImpl authService;
    private final JwtService jwtService;


    @PostMapping("/signup")
    public Mono<ResponseWorkerDto> registerUser(@RequestBody CreateWorkerDto request) {
        return authService.registerUser(request);

    }

    @PostMapping("/login")
    public Mono<JwtAuthenticationDto> login( @RequestBody UserCredentialsDto workerCredentialsDto) {
        return authService.loginUser(workerCredentialsDto);

    }

    @PostMapping("/refresh/token")
    public Mono<JwtAuthenticationDto> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refreshTokens(refreshTokenDto);
    }

}