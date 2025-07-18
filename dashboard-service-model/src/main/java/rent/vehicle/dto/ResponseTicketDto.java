package rent.vehicle.dto;

import lombok.Getter;
import lombok.Setter;
import rent.vehicle.enums.TicketStatus;

@Setter
@Getter
public class ResponseTicketDto {
    private Long id;
    private String createdByUserId;
    private String createdByUserName;
    private String header;
    private String problem;
    private TicketStatus status;
    private ResponseWorkerDto assignedTo;
}
