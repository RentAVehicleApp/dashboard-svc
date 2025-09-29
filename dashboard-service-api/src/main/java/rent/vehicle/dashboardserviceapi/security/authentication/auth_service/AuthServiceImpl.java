package rent.vehicle.dashboardserviceapi.security.authentication.auth_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.security.jwt.JwtFilter;
import rent.vehicle.dashboardserviceapi.security.jwt.JwtService;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardServiceImpl;
import rent.vehicle.security.JwtAuthenticationDto;
import rent.vehicle.security.RefreshTokenDto;
import rent.vehicle.security.UserCredentialsDto;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.WorkerAuthDto;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Set<String> ALLOWED_ROLES = Set.of("SUPPORTER", "TECHNICAL", "ADMIN");
    private final PasswordEncoder passwordEncoder;
    private final WorkerDashboardServiceImpl workerDashboardService;
    private final JwtService jwtService;
    @Override
    public Mono<ResponseWorkerDto> registerUser(CreateWorkerDto createWorkerDto) {
        log.info("Password before encoding of new worker is {} ", createWorkerDto.getPassword());
        createWorkerDto.setPassword(passwordEncoder.encode(createWorkerDto.getPassword()));
        log.info("Password of new worker is {}", createWorkerDto.getPassword());
        Set<String> validRoles = createWorkerDto.getRoles() != null
                ? createWorkerDto.getRoles().stream()
                .map(String::toUpperCase)
                .filter(ALLOWED_ROLES::contains)
                .collect(Collectors.toSet())
                : new HashSet<>();

        // Если нет валидных ролей - добавляем SUPPORTER
        if (validRoles.isEmpty()) {
            validRoles.add("SUPPORTER");
        }
        createWorkerDto.setRoles(validRoles);
        return  workerDashboardService.createWorker(createWorkerDto);
    }

    @Override
    public Mono<JwtAuthenticationDto> loginUser(UserCredentialsDto credentialsDto) {

        return workerDashboardService.findWorkerByLogin(credentialsDto.getLogin())
                .flatMap(workerAuthDto -> {
                    if (passwordEncoder.matches(credentialsDto.getPassword(), workerAuthDto.password())) {
                        JwtAuthenticationDto tokens = jwtService.generateAuthToken(workerAuthDto.login(),
                                new HashSet<>(workerAuthDto.roles()));
                        return Mono.just(tokens);
                    }
                    else {
                        // Пароль не совпал - возвращаем ошибку
                        return Mono.error(new AuthenticationException("Invalid password"));
                    }
                })  .switchIfEmpty(Mono.error(new AuthenticationException("User not found")));
    }


    @Override
    public Mono<JwtAuthenticationDto> refreshTokens(RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (!jwtService.validateToken(refreshToken)) {
            return Mono.error(new Exception("Invalid refresh token")); //TODO добавить кастомный Exception
        }
        return workerDashboardService.findWorkerByLogin(jwtService.getLoginFromToken(refreshToken))
                .flatMap(worker->{
                    JwtAuthenticationDto tokens = jwtService
                            .refreshBaseToken(worker.login(),refreshToken,new HashSet<>(worker.roles()));
                    return Mono.just(tokens);
                }).switchIfEmpty(Mono.error(new AuthenticationException("Invalid token")));
    }
}