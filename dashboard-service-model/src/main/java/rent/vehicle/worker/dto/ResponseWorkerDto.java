package rent.vehicle.worker.dto;

import lombok.Getter;
import lombok.Setter;
import rent.vehicle.ticket.dto.ResponseTicketDto;

import java.util.Set;
@Getter
@Setter
public class ResponseWorkerDto {
    private Long id;
    private String login;
    private String name;
    private Set<ResponseTicketDto> assignedTickets;
}
