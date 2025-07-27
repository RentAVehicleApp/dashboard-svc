package rent.vehicle.dashboardserviceapi.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import rent.vehicle.device.constants.ApiPaths;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{

    private final WebClient deviceWebClient;

    @Override
    public String testService() {
        return "Hello world";
//        return deviceWebClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path(ApiPaths.PATH_VEHICLE)
//                        .build())
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
    }
}
