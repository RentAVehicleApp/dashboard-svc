package rent.vehicle.dashboardserviceapi.security.auth_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.security.jwt.JwtService;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardServiceImpl;
import rent.vehicle.security.JwtAuthenticationDto;
import rent.vehicle.security.RefreshTokenDto;
import rent.vehicle.security.UserCredentialsDto;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.WorkerAuthDto;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final WorkerDashboardServiceImpl workerDashboardService;
    private final JwtService jwtService;


    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody CreateWorkerDto request) {
        CreateWorkerDto createDto = new CreateWorkerDto(
                request.getLogin(),
                request.getName(),
                Set.of("USER")
        );

        // Отправляем в Worker Service
        return workerDashboardService.createWorker(createDto)
                .map(worker -> ResponseEntity.ok("User registered successfully"))
                .onErrorResume(WebClientResponseException.Conflict.class,
                        e -> Mono.just(ResponseEntity.badRequest().body("Username already taken")));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<JwtAuthenticationDto>> login(@RequestBody UserCredentialsDto workerCredentialsDto) {
        return workerDashboardService.findWorkerByLogin(workerCredentialsDto.getLogin())
                .flatMap(workerAuthDto -> {
                    if (workerAuthDto.password().equals(workerCredentialsDto.getPassword())) {
                        JwtAuthenticationDto token = jwtService.generateAuthToken(workerAuthDto.login());
                        return Mono.just(ResponseEntity.ok(token));
                    } else {
                        return Mono.just(
                                ResponseEntity
                                        .<JwtAuthenticationDto>status(HttpStatus.UNAUTHORIZED)
                                        .build()
                        );
                    }
                });
    }

    @PostMapping("/refresh/token")
    public Mono<ResponseEntity<JwtAuthenticationDto>> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        return Mono.justOrEmpty(refreshTokenDto.getRefreshToken())
                // 1) локальная проверка подписи/срока refresh-токена
                .filter(jwtService::validateToken)
                // 2) извлечь login из refresh-токена
                .map(jwtService::getLoginFromToken)
                // 3) получить WorkerAuthDto реактивно
                .flatMap(workerDashboardService::findWorkerByLogin)
                // 4) сгенерировать новую пару токенов
                .flatMap(workerAuthDto -> {
                    JwtAuthenticationDto tokens = jwtService.refreshBaseToken(
                            workerAuthDto.login(),
                            refreshTokenDto.getRefreshToken()
                    );
                    return Mono.just(ResponseEntity.ok(tokens));
                })
                .defaultIfEmpty(
                        ResponseEntity
                                .<JwtAuthenticationDto>status(HttpStatus.UNAUTHORIZED)
                                .build()
                );
        }

}






