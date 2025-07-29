package rent.vehicle.dashboardserviceapi.common.service;

public interface KeepAliveService {
    String callDeviceService();
    String callCustomerService();
    String callWorkerService();
}