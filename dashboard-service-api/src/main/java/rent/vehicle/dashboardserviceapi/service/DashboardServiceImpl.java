package rent.vehicle.dashboardserviceapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rent.vehicle.dto.*;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final WebClient customerServiceWebClient;
    private final WebClient workerServiceWebClient;
    // Worker methods implementation
    @Override
    public Mono<ResponseWorkerDto> createWorker(CreateWorkerDto createWorkerDto) {
        return workerServiceWebClient
                .post()
                .uri("/api/workers")
                .bodyValue(createWorkerDto)
                .retrieve()
                .bodyToMono(ResponseWorkerDto.class);
    }

    @Override
    public Mono<ResponseWorkerDto> updateWorker(Long id, UpdateWorkerDto updateWorkerDto) {
        return workerServiceWebClient
                .post()
                .uri("/api/workers/supporter/{id}/update", id)
                .bodyValue(updateWorkerDto)
                .retrieve()
                .bodyToMono(ResponseWorkerDto.class);

    }

    @Override
    public Mono<ResponseWorkerDto> findWorker(Long id) {
        return workerServiceWebClient
                .get()
                .uri("/api/workers/supporter/{id}", id)
                .retrieve()
                .bodyToMono(ResponseWorkerDto.class);

    }

    @Override
    public Mono<Boolean> removeWorker(Long id) {
        return workerServiceWebClient
                .delete()
                .uri("/api/workers/supporter/{id}", id)
                .retrieve()
                .bodyToMono(Boolean.class);

    }

    // Ticket methods implementation
    @Override
    public Mono<ResponseTicketDto> createTicket(CreateTicketDto createTicketDto) {

        return workerServiceWebClient
                .post()
                .uri("/api/workers/ticket")
                .bodyValue(createTicketDto)
                .retrieve()
                .bodyToMono(ResponseTicketDto.class);

    }

    @Override
    public Mono<ResponseTicketDto> updateTicket(Long id, UpdateTicketDto updateTicketDto) {

        return workerServiceWebClient
                .post()
                .uri("/api/workers/ticket/{id}", id)
                .bodyValue(updateTicketDto)
                .retrieve()
                .bodyToMono(ResponseTicketDto.class);

    }

    @Override
    public Mono<ResponseTicketDto> findTicket(Long id) {

        return workerServiceWebClient
                .get()
                .uri("/api/workers/ticket/{id}", id)
                .retrieve()
                .bodyToMono(ResponseTicketDto.class);

    }

    @Override
    public Mono<Boolean> removeTicket(Long id) {
        return workerServiceWebClient
                .delete()
                .uri("/api/workers/ticket/{id}", id)
                .retrieve()
                .bodyToMono(Boolean.class);

    }

    @Override
    public Mono<CustomerResponse> createCustomer(CreateCustomerDto createCustomerDto) {
        return customerServiceWebClient
                .post()
                .uri("/api/customers")
                .bodyValue(createCustomerDto)
                .retrieve()
                .bodyToMono(CustomerResponse.class);

    }

    @Override
    public Mono<CustomerResponse> getCustomer(Long userId) {
        return customerServiceWebClient
                .get()
                .uri("/api/customers/{userId}", userId)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }

    @Override
    public Mono<CustomerResponse> updateCustomer(Long userId, UpdateCustomerDto updateCustomerDto) {
        return customerServiceWebClient
                .patch()
                .uri("/api/customers/update/{userId}", userId)
                .bodyValue(updateCustomerDto)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }

    @Override
    public Mono<CustomerResponse> removeCustomer(Long userId) {
        return customerServiceWebClient
                .delete()
                .uri("/api/customers/remove/{userId}", userId)
                .retrieve()
                .bodyToMono(CustomerResponse.class);

    }
}
