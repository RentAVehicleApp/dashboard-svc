package rent.vehicle.dashboardserviceapi.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.customer.dto.CreateCustomerDto;
import rent.vehicle.customer.dto.CustomerResponse;
import rent.vehicle.customer.dto.UpdateCustomerDto;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.customer.customer_service.CustomerDashboardService;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {


    private final CustomerDashboardService customerDashboardService;


    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerResponse> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return customerDashboardService.createCustomer(createCustomerDto);
    }

    @GetMapping("/customers/{userId}")
    public Mono<CustomerResponse> getCustomer(@PathVariable Long userId) {
        return customerDashboardService.getCustomer(userId);
    }

    @PatchMapping("/customers/update/{userId}")
    public Mono<CustomerResponse> updateCustomer(@PathVariable Long userId,
                                                 @RequestBody UpdateCustomerDto updateCustomerDto) {
        return customerDashboardService.updateCustomer(userId, updateCustomerDto);
    }

    @DeleteMapping("/customers/remove/{userId}")
    public Mono<CustomerResponse> deleteCustomer(@PathVariable Long userId) {
        return customerDashboardService.removeCustomer(userId);
    }

    @GetMapping("/customers/all")
    public Mono<CustomPage<CustomerResponse>> getAllCustomers(Pageable pageable) {
        return customerDashboardService.getAll(pageable);
    }

    @GetMapping("/customers/search")
    public Mono<CustomPage<CustomerResponse>> searchCustomersByParams( @RequestParam(required = false) String filter,Pageable pageable){
        return customerDashboardService.searchCustomers(filter,pageable);
    }
}
