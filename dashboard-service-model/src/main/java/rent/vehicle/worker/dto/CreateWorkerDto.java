package rent.vehicle.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateWorkerDto {
    private String login;
    private String name;
    private Collection<String> roles;
}
