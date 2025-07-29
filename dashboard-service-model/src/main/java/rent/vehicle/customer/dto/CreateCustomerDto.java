package rent.vehicle.customer.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class CreateCustomerDto {


    private String firstName;


    private String lastName;


    private String email;



    private String phoneNumber;

    private LocalDate birthDate;


    private CustomerLicenseType licenseType;


    private String drivingLicenseNumber;

    private Instant createdAt;


}
