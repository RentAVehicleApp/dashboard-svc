package rent.vehicle.dashboardserviceapi.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rent.vehicle.dashboardserviceapi.common.service.KeepAliveService;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final KeepAliveService keepAliveService;

    @GetMapping()
    public String testService() {
        StringBuilder answer = new StringBuilder();
        answer.append("log1: ").append(keepAliveService.callCustomerService());
//        answer.append("log2: ").append(keepAliveService.callWorkerService());
//        answer.append("log3: ").append(keepAliveService.callDeviceService());

        return answer.toString();
    }
}
