package rent.vehicle.dashboardserviceapi.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rent.vehicle.dashboardserviceapi.common.service.KeepAliveService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final KeepAliveService keepAliveService;

    @GetMapping()
    public String testService() {
        log.info("Received request to /testService");

        StringBuilder answer = new StringBuilder();
        try {
            String customerResponse = keepAliveService.callCustomerService();
            log.debug("Customer service response: {}", customerResponse);
            answer.append("log1: ").append(customerResponse);

            String workerResponse = keepAliveService.callWorkerService();
            log.debug("Worker service response: {}", workerResponse);
            answer.append("log2: ").append(workerResponse);

            String deviceResponse = keepAliveService.callDeviceService();
            log.debug("Device service response: {}", deviceResponse);
            answer.append("log3: ").append(deviceResponse);

        } catch (Exception e) {
            log.error("Error in testService", e);
            throw e;
        }

        return answer.toString();
    }
}
