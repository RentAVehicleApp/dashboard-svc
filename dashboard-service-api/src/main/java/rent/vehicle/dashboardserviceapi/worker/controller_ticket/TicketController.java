package rent.vehicle.dashboardserviceapi.worker.controller_ticket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.worker.worker_service.ticket_service.TicketDashboardService;
import rent.vehicle.ticket.dto.CreateTicketDto;
import rent.vehicle.ticket.dto.ResponseTicketDto;
import rent.vehicle.ticket.dto.UpdateTicketDto;
@Slf4j
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
        log.info("Request to create ticket: {}", createTicketDto);
        return ticketDashboardService.createTicket(createTicketDto)
                .doOnSuccess(ticket -> log.info("Successfully created ticket: {}", ticket))
                .doOnError(e -> log.error("Error creating ticket: {}", createTicketDto, e));
    }

    @PostMapping("/workers/ticket/{id}")
    public Mono<ResponseTicketDto> updateTicket(@PathVariable Long id,
                                                @RequestBody UpdateTicketDto updateTicketDto) {
        log.info("Request to update ticket id={}, data={}", id, updateTicketDto);
        return ticketDashboardService.updateTicket(id, updateTicketDto)
                .doOnSuccess(ticket -> log.info("Successfully updated ticket: {}", ticket))
                .doOnError(e -> log.error("Error updating ticket id={}, data={}", id, updateTicketDto, e));
    }

    @GetMapping("/workers/ticket/{id}")
    public Mono<ResponseTicketDto> getTicket(@PathVariable Long id) {
        log.debug("Request to get ticket with id={}", id);
        return ticketDashboardService.findTicket(id)
                .doOnSuccess(ticket -> log.debug("Found ticket: {}", ticket))
                .doOnError(e -> log.error("Error fetching ticket with id={}", id, e));
    }

    @DeleteMapping("/workers/ticket/{id}")
    public Mono<Boolean> deleteTicket(@PathVariable Long id) {
        log.info("Request to delete ticket with id={}", id);
        return ticketDashboardService.removeTicket(id)
                .doOnSuccess(result -> log.info("Successfully deleted ticket id={}, result={}", id, result))
                .doOnError(e -> log.error("Error deleting ticket with id={}", id, e));
    }

    @GetMapping("/tickets/all")
    public Mono<CustomPage<ResponseTicketDto>> getAllTickets(Pageable pageable) {
        log.debug("Request to get all tickets, pageable={}", pageable);
        return ticketDashboardService.getAll(pageable)
                .doOnSuccess(page -> log.debug("Found {} tickets", page.getContent().size()))
                .doOnError(e -> log.error("Error fetching all tickets, pageable={}", pageable, e));
    }

    @GetMapping("/tickets/search")
    public Mono<CustomPage<ResponseTicketDto>> searchTicketsByParams( @RequestParam(required = false) String filter,Pageable pageable) {
        log.debug("Request to search tickets with filter='{}', pageable={}", filter, pageable);
        return ticketDashboardService.searchTickets(filter, pageable)
                .doOnSuccess(page -> log.debug("Found {} tickets by filter='{}'", page.getContent().size(), filter))
                .doOnError(e -> log.error("Error searching tickets with filter='{}', pageable={}", filter, pageable, e));
    }
}
