package rent.vehicle.specification.dto;

import lombok.Getter;
import lombok.Setter;
import rent.vehicle.specification.enums.Operations;

@Getter
@Setter
public class SearchCriteria {
    String filter;
    Operations operation;
    String value;
}
