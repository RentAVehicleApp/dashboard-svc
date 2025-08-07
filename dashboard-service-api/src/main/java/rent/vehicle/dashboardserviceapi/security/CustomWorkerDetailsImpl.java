package rent.vehicle.dashboardserviceapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardServiceImpl;
import rent.vehicle.worker.dto.WorkerAuthDto;

@Service
@RequiredArgsConstructor
public class CustomWorkerDetailsImpl implements UserDetailsService {
    private final WorkerDashboardServiceImpl workerDashboardService;
    @Override
    public CustomWorkerDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try{
            WorkerAuthDto workerAuthDto = workerDashboardService
                    .findWorkerByLogin(login)
                    .block();
            if (workerAuthDto == null) {
                throw new UsernameNotFoundException("Worker not found: " + login);
            }
            return new CustomWorkerDetails(workerAuthDto);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Worker not found: " + login, e);
        }
        }

    }

