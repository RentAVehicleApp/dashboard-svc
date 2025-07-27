package rent.vehicle.dashboardserviceapi.device.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rent.vehicle.dashboardserviceapi.device.service.TestService;

@RestController
public class TestController {
    TestService testService;

    @GetMapping
    public String testService() {
        return testService.testService();
    }
}
