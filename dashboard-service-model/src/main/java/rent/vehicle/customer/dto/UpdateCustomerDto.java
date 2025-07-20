package rent.vehicle.customer.dto;

import lombok.Getter;
import rent.vehicle.customer.enums.CustomerLicenseType;

import java.time.Instant;

@Getter
public class UpdateCustomerDto {

    private String userFirstName;
    private String userLastName;
    private String userEmail;

    private String userPhoneNumber;

    private CustomerLicenseType userLicense;


    private Instant updatedAt;


}
