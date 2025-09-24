package rent.vehicle.dashboardserviceapi.security.jwt;

import com.auth0.jwt.JWTVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rent.vehicle.dashboardserviceapi.security.CustomWorkerDetails;
import rent.vehicle.dashboardserviceapi.security.CustomWorkerDetailsImpl;

import java.io.IOException;
import java.net.http.HttpHeaders;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomWorkerDetailsImpl customWorkerDetailsImpl;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if(token != null && jwtService.validateToken(token) ) {
            setCustomWorkerDetailsSecurityContextHolder(token);
        }
    filterChain.doFilter(request, response);
    }

    private void setCustomWorkerDetailsSecurityContextHolder(String token) {
        String login = jwtService.getLoginFromToken(token);
        CustomWorkerDetails customWorkerDetails = customWorkerDetailsImpl.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customWorkerDetails,
                null, customWorkerDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    private String getTokenFromRequest(@NonNull HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null &&  bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
    return null;
    }
}
