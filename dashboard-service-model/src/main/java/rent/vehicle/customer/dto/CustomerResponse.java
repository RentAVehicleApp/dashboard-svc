package rent.vehicle.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rent.vehicle.customer.enums.CustomerLicenseType;
import rent.vehicle.customer.enums.CustomerStatus;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;  // Изменено с userId на id
    private String firstName;  // Изменено с userFirstName
    private String lastName;   // Изменено с userLastName
    private String email;      // Изменено с userEmail
    private String phoneNumber; // Изменено с userPhoneNumber
    private LocalDate birthDate; // Изменено с userBirthDate
    private CustomerLicenseType licenseType; // Изменено с userLicense
    private String drivingLicenseNumber; // Оставлено как есть
    private Instant createdAt;
    private Instant updatedAt;
    private CustomerStatus status; // Добавьте статус
}