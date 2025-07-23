package rent.vehicle.specification.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenericSearchRequest {
    private List<SearchCriteria> searchCriteriaList;
    private int page = 0;
    private int size = 20;
    private String sort = "id,desc";
}
