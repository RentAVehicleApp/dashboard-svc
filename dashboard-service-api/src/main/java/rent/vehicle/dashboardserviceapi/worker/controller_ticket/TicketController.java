package rent.vehicle.dashboardserviceapi.worker.controller_ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.customer.customer_service.CustomerDashboardService;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardService;
import rent.vehicle.dashboardserviceapi.worker.worker_service.ticket_service.TicketDashboardService;
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
public class TicketController {


    private final TicketDashboardService ticketDashboardService;


    // Worker endpoints
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

    @GetMapping("/tickets/all")
    public Mono<CustomPage<ResponseTicketDto>> getAllTickets(Pageable pageable) {
        return ticketDashboardService.getAll(pageable);
    }
    @GetMapping("/tickets/search")
    public Mono<CustomPage<ResponseTicketDto>> searchTicketsByParams( @RequestParam(required = false) String filter,Pageable pageable) {
        return ticketDashboardService.searchTickets(filter,pageable);
    }
}
