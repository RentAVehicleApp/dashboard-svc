package rent.vehicle.dashboardserviceapi.service;

import reactor.core.publisher.Mono;
import rent.vehicle.dto.*;

public interface DashboardService {
    Mono<ResponseWorkerDto> createWorker(CreateWorkerDto createWorkerDto);

    Mono<ResponseWorkerDto> updateWorker(Long id, UpdateWorkerDto updateWorkerDto);

    Mono<ResponseWorkerDto> findWorker(Long id);

    Mono<Boolean> removeWorker(Long id);

    Mono<ResponseTicketDto> createTicket(CreateTicketDto createTicketDto);

    Mono<ResponseTicketDto> updateTicket(Long id, UpdateTicketDto updateTicketDto);

    Mono<ResponseTicketDto> findTicket(Long id);

    Mono<Boolean> removeTicket(Long id);

    Mono<CustomerResponse> createCustomer(CreateCustomerDto createCustomerDto);

    Mono<CustomerResponse> getCustomer(Long userId);

    Mono<CustomerResponse> updateCustomer(Long userId, UpdateCustomerDto updateCustomerDto);

    Mono<CustomerResponse> removeCustomer(Long userId);
}
