package rent.vehicle.dashboardserviceapi.security.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rent.vehicle.dashboardserviceapi.security.CustomUserDetails;
import rent.vehicle.dashboardserviceapi.security.dto.LoginRequestDto;
import rent.vehicle.dashboardserviceapi.security.dto.token.RefreshTokenResponseDto;
import rent.vehicle.dashboardserviceapi.security.dto.token.TokenResponseDto;
import rent.vehicle.dashboardserviceapi.security.service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    // todo . Добавить валидацию
    //java@PostMapping("/login")
    //public ResponseEntity<TokenResponseDto> loginCustomer(
    //    @Valid @RequestBody LoginRequestDto loginRequestDto) {
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginCustomer( @RequestBody LoginRequestDto loginRequestDto) {
        return authService.loginCustomer(loginRequestDto);
    }

    @PostMapping("/refresh" )
    public ResponseEntity<RefreshTokenResponseDto> refreshAccessToken(@RequestHeader("x-refresh-token") String refreshToken) {
        return authService.refreshCustomerAccessToken(refreshToken);
    }

    @DeleteMapping("/logout")
    //todo стоит включить проверку роли.
//    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> logoutCustomer(@AuthenticationPrincipal CustomUserDetails user) {
        return authService.logoutCustomer(user.getId());
    }

}