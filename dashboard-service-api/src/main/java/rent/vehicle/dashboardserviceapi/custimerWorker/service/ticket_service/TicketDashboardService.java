package rent.vehicle.dashboardserviceapi.custimerWorker.service.ticket_service;

import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.specification.dto.GenericSearchRequest;
import rent.vehicle.ticket.dto.CreateTicketDto;
import rent.vehicle.ticket.dto.ResponseTicketDto;
import rent.vehicle.ticket.dto.UpdateTicketDto;

import org.springframework.data.domain.Pageable;

public interface TicketDashboardService {
    Mono<ResponseTicketDto> createTicket(CreateTicketDto createTicketDto);

    Mono<ResponseTicketDto> updateTicket(Long id, UpdateTicketDto updateTicketDto);

    Mono<ResponseTicketDto> findTicket(Long id);

    Mono<Boolean> removeTicket(Long id);

    Mono<CustomPage<ResponseTicketDto>> getAll(Pageable pageable);

    Mono<CustomPage<ResponseTicketDto>> searchTickets(GenericSearchRequest genericSearchRequest);
}
