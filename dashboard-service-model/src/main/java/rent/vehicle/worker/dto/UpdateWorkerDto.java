package rent.vehicle.worker.dto;

import lombok.Getter;
import lombok.Setter;
import rent.vehicle.ticket.dto.CreateTicketDto;


import java.util.Set;

@Setter
@Getter
public class UpdateWorkerDto {
    private String login;
    private String name;
    private Set<CreateTicketDto> assignedTickets;
}
