package rent.vehicle.worker.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
public record WorkerAuthDto(
        Long id,
        String login,
        Collection<String> roles
) {}