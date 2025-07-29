package rent.vehicle.dashboardserviceapi.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KeepAliveServiceImpl implements KeepAliveService {

    private final WebClient deviceWebClient;
    private final WebClient customerServiceWebClient;
    private final WebClient workerServiceWebClient;

    @Scheduled(fixedRate =  600_000)
    @Override
    public String callDeviceService() {
        String answer =  deviceWebClient.get()
                .uri("/") // 👈 корень
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("log: " + answer);
        return answer;
    }

    @Scheduled(fixedRate =  600_000)
    @Override
    public String callCustomerService() {
        String answer =  customerServiceWebClient.get()
                .uri(("/api/customers/1")) // 👈 корень
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("log: " + answer);
        return answer;
    }

    @Scheduled(fixedRate =  600_000)
    @Override
    public String callWorkerService() {
        String answer =  workerServiceWebClient.get()
                .uri("/api/v1/workers/ticket/1") // 👈 корень
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("log: " + answer);
        return answer;
    }
}
