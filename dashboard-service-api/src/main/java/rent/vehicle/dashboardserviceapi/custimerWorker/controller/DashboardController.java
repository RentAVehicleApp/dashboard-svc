package rent.vehicle.dashboardserviceapi.custimerWorker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.customer.dto.CreateCustomerDto;
import rent.vehicle.customer.dto.CustomerResponse;
import rent.vehicle.customer.dto.UpdateCustomerDto;
import rent.vehicle.dashboardserviceapi.custimerWorker.service.customer_service.CustomerDashboardService;
import rent.vehicle.dashboardserviceapi.custimerWorker.service.ticket_service.TicketDashboardService;
import rent.vehicle.dashboardserviceapi.custimerWorker.service.worker_service.WorkerDashboardService;
import rent.vehicle.specification.dto.GenericSearchRequest;
import rent.vehicle.ticket.dto.CreateTicketDto;
import rent.vehicle.ticket.dto.ResponseTicketDto;
import rent.vehicle.ticket.dto.UpdateTicketDto;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.UpdateWorkerDto;





@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final CustomerDashboardService customerDashboardService;
    private final TicketDashboardService ticketDashboardService;
    private final WorkerDashboardService workerDashboardService;

    // Worker endpoints
    @PostMapping("/workers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseWorkerDto> createWorker(@RequestBody CreateWorkerDto createWorkerDto) {
        return workerDashboardService.createWorker(createWorkerDto);
    }

    @PostMapping("/workers/supporter/{id}/update")
    public Mono<ResponseWorkerDto> updateSupporter(@PathVariable Long id,
                                                   @RequestBody UpdateWorkerDto updateWorkerDto) {
        return workerDashboardService.updateWorker(id, updateWorkerDto);
    }

    @GetMapping("/workers/supporter/{id}")
    public Mono<ResponseWorkerDto> getSupporter(@PathVariable Long id) {
        return workerDashboardService.findWorker(id);
    }

    @DeleteMapping("/workers/supporter/{id}")
    public Mono<Boolean> deleteSupporter(@PathVariable Long id) {
        return workerDashboardService.removeWorker(id);
    }

    // Ticket endpoints
    @PostMapping("/workers/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseTicketDto> createTicket(@RequestBody CreateTicketDto createTicketDto) {
        return ticketDashboardService.createTicket(createTicketDto);
    }

    @PostMapping("/workers/ticket/{id}")
    public Mono<ResponseTicketDto> updateTicket(@PathVariable Long id,
                                                @RequestBody UpdateTicketDto updateTicketDto) {
        return ticketDashboardService.updateTicket(id, updateTicketDto);
    }

    @GetMapping("/workers/ticket/{id}")
    public Mono<ResponseTicketDto> getTicket(@PathVariable Long id) {
        return ticketDashboardService.findTicket(id);
    }

    @DeleteMapping("/workers/ticket/{id}")
    public Mono<Boolean> deleteTicket(@PathVariable Long id) {
        return ticketDashboardService.removeTicket(id);
    }

    // Customer endpoints
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerResponse> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return customerDashboardService.createCustomer(createCustomerDto);
    }

    @GetMapping("/customers/{userId}")
    public Mono<CustomerResponse> getCustomer(@PathVariable Long userId) {
        return customerDashboardService.getCustomer(userId);
    }

    @PatchMapping("/customers/update/{userId}")
    public Mono<CustomerResponse> updateCustomer(@PathVariable Long userId,
                                                 @RequestBody UpdateCustomerDto updateCustomerDto) {
        return customerDashboardService.updateCustomer(userId, updateCustomerDto);
    }

    @DeleteMapping("/customers/remove/{userId}")
    public Mono<CustomerResponse> deleteCustomer(@PathVariable Long userId) {
        return customerDashboardService.removeCustomer(userId);
    }
    @GetMapping("/workers/all")
    public Mono<CustomPage<ResponseWorkerDto>> getAllWorkers(Pageable pageable) {
        return workerDashboardService.getAll(pageable);
    }
    @GetMapping("/tickets/all")
    public Mono<CustomPage<ResponseTicketDto>> getAllTickets(Pageable pageable) {
        return ticketDashboardService.getAll(pageable);
    }
    @GetMapping("/customers/all")
    public Mono<CustomPage<CustomerResponse>> getAllCustomers(Pageable pageable) {
        return customerDashboardService.getAll(pageable);
    }
    @GetMapping("/workers/search")
    public Mono<CustomPage<ResponseWorkerDto>> searchWorkersByParams(@ModelAttribute GenericSearchRequest genericSearchRequest) {
        return workerDashboardService.searchWorkers(genericSearchRequest);
    }

    @GetMapping("/tickets/search")
    public Mono<CustomPage<ResponseTicketDto>> searchTicketsByParams(@ModelAttribute GenericSearchRequest genericSearchRequest) {
        return ticketDashboardService.searchTickets(genericSearchRequest);
    }
    @GetMapping("/customers/search")
        public Mono<CustomPage<CustomerResponse>> searchCustomersByParams(@ModelAttribute GenericSearchRequest genericSearchRequest){
            return customerDashboardService.searchCustomers(genericSearchRequest);
        }

}