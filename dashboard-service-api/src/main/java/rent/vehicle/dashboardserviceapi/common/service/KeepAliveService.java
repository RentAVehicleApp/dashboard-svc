package rent.vehicle.dashboardserviceapi.common.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import rent.vehicle.dashboardserviceapi.customer.customer_service.CustomerDashboardService;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardService;
import rent.vehicle.dashboardserviceapi.device.service.DeviceConfigService;

@Service
@RequiredArgsConstructor
public class KeepAliveService {

    private final WebClient deviceWebClient;
    private final WebClient customerServiceWebClient;
    private final WebClient workerServiceWebClient;

    private final DeviceConfigService deviceConfigService;
    private final WorkerDashboardService workerDashboardService;
    private final CustomerDashboardService customerDashboardService;

    @PostConstruct
    public void doWork() throws InterruptedException {

        Runnable task = new Runnable() {
            public void run() {
                System.out.println("log: start KeepAliveService");
                System.out.println(deviceConfigService.findDeviceConfigById(1L));
                System.out.println(workerDashboardService.findWorker(1L));
                System.out.println(customerDashboardService.getCustomer(1L));

            }
        };

        while (true) {
            Thread thread = new Thread(task);
            thread.start();
            Thread.sleep(40000);

        }
    }


}
