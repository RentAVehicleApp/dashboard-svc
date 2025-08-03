package rent.vehicle.dashboardserviceapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardServiceImpl;

@Service
@RequiredArgsConstructor
public class CustomWorkerDetailsImpl implements UserDetailsService {
    private final WorkerDashboardServiceImpl workerDashboardService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return workerDashboardService.findWorker();
    }
}
