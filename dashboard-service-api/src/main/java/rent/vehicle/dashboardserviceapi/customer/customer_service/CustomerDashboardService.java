package rent.vehicle.dashboardserviceapi.customer.customer_service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.customer.dto.CreateCustomerDto;
import rent.vehicle.customer.dto.CustomerResponse;
import rent.vehicle.customer.dto.UpdateCustomerDto;



public interface CustomerDashboardService {
    Mono<CustomerResponse> createCustomer(CreateCustomerDto createCustomerDto);

    Mono<CustomerResponse> getCustomer(Long userId);

    Mono<CustomerResponse> updateCustomer(Long userId, UpdateCustomerDto updateCustomerDto);

    Mono<CustomerResponse> removeCustomer(Long userId);

    Mono<CustomPage<CustomerResponse>> getAll(Pageable pageable);

    Mono<CustomPage<CustomerResponse>> searchCustomers(String filter, Pageable pageable);
}