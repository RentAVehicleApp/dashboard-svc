package rent.vehicle.dashboardserviceapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.service.DashboardService;
import rent.vehicle.dto.*;


@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    // Worker endpoints
    @PostMapping("/workers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseWorkerDto> createWorker(@RequestBody CreateWorkerDto createWorkerDto) {
        return dashboardService.createWorker(createWorkerDto);
    }

    @PostMapping("/workers/supporter/{id}/update")
    public Mono<ResponseWorkerDto> updateSupporter(@PathVariable Long id,
                                                   @RequestBody UpdateWorkerDto updateWorkerDto) {
        return dashboardService.updateWorker(id, updateWorkerDto);
    }

    @GetMapping("/workers/supporter/{id}")
    public Mono<ResponseWorkerDto> getSupporter(@PathVariable Long id) {
        return dashboardService.findWorker(id);
    }

    @DeleteMapping("/workers/supporter/{id}")
    public Mono<Boolean> deleteSupporter(@PathVariable Long id) {
        return dashboardService.removeWorker(id);
    }

    // Ticket endpoints
    @PostMapping("/workers/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseTicketDto> createTicket(@RequestBody CreateTicketDto createTicketDto) {
        return dashboardService.createTicket(createTicketDto);
    }

    @PostMapping("/workers/ticket/{id}")
    public Mono<ResponseTicketDto> updateTicket(@PathVariable Long id,
                                                @RequestBody UpdateTicketDto updateTicketDto) {
        return dashboardService.updateTicket(id, updateTicketDto);
    }

    @GetMapping("/workers/ticket/{id}")
    public Mono<ResponseTicketDto> getTicket(@PathVariable Long id) {
        return dashboardService.findTicket(id);
    }

    @DeleteMapping("/workers/ticket/{id}")
    public Mono<Boolean> deleteTicket(@PathVariable Long id) {
        return dashboardService.removeTicket(id);
    }

    // Customer endpoints
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerResponse> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return dashboardService.createCustomer(createCustomerDto);
    }

    @GetMapping("/customers/{userId}")
    public Mono<CustomerResponse> getCustomer(@PathVariable Long userId) {
        return dashboardService.getCustomer(userId);
    }

    @PatchMapping("/customers/update/{userId}")
    public Mono<CustomerResponse> updateCustomer(@PathVariable Long userId,
                                                 @RequestBody UpdateCustomerDto updateCustomerDto) {
        return dashboardService.updateCustomer(userId, updateCustomerDto);
    }

    @DeleteMapping("/customers/remove/{userId}")
    public Mono<CustomerResponse> deleteCustomer(@PathVariable Long userId) {
        return dashboardService.removeCustomer(userId);
    }
}