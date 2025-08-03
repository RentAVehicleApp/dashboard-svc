package rent.vehicle.dashboardserviceapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.WorkerAuthDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public record CustomWorkerDetails (WorkerAuthDto workerAuthDto) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (workerAuthDto.roles() == null) {
            return java.util.List.of();
        }
        return workerAuthDto.roles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
                .collect(Collectors.toSet());
    }



    @Override
    public String getPassword() {
        return "" ;
    }

    @Override
    public String getUsername() {
        return workerAuthDto.login();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
