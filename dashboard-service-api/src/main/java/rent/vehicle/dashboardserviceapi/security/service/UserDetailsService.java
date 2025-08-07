package rent.vehicle.dashboardserviceapi.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rent.vehicle.dashboardserviceapi.security.CustomUserDetails;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardService;
import rent.vehicle.worker.dto.ResponseWorkerDto;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final WorkerDashboardService workerDashboardService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ResponseWorkerDto worker = workerDashboardService.searchWorkers("mail", Pageable.unpaged() )
                    .block();

            if (worker == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return CustomUserDetails.builder()
                    .id(worker.getId())
                    .user(worker.getLogin())
                    .build();
        }
        // Это тот пользователь, который логинится в систему
    } catch (Exception e) {
        throw new UsernameNotFoundException("Error loading worker: " + username, e);
    }


}
