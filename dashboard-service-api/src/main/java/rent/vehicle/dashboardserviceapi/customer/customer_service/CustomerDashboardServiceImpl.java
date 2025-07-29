package rent.vehicle.dashboardserviceapi.customer.customer_service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import rent.vehicle.dashboardserviceapi.common.config.QueryParamUtil;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.customer.dto.CreateCustomerDto;
import rent.vehicle.customer.dto.CustomerResponse;
import rent.vehicle.customer.dto.UpdateCustomerDto;





@Service
@RequiredArgsConstructor
public class CustomerDashboardServiceImpl implements CustomerDashboardService {
    private final WebClient customerServiceWebClient;

    // Ticket methods implementation

    @Override
    public Mono<CustomerResponse> createCustomer(CreateCustomerDto createCustomerDto) {
        return customerServiceWebClient
                .post()
                .uri("/api/customers")
                .bodyValue(createCustomerDto)
                .retrieve()
                .bodyToMono(CustomerResponse.class);

    }

    @Override
    public Mono<CustomerResponse> getCustomer(Long userId) {
        return customerServiceWebClient
                .get()
                .uri("/api/customers/{userId}", userId)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }

    @Override
    public Mono<CustomerResponse> updateCustomer(Long userId, UpdateCustomerDto updateCustomerDto) {
        return customerServiceWebClient
                .patch()
                .uri("/api/customers/update/{userId}", userId)
                .bodyValue(updateCustomerDto)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }

    @Override
    public Mono<CustomerResponse> removeCustomer(Long userId) {
        return customerServiceWebClient
                .delete()
                .uri("/api/customers/remove/{userId}", userId)
                .retrieve()
                .bodyToMono(CustomerResponse.class);

    }

    @Override
    public Mono<CustomPage<CustomerResponse>> getAll(Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(null,pageable);

        return customerServiceWebClient
                .get()
                .uri(uriBuilder ->uriBuilder
                        .path("/api/v1/users/all")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<CustomerResponse>>(){});
    }

    @Override
    public Mono<CustomPage<CustomerResponse>> searchCustomers(String filter, Pageable pageable) {
       MultiValueMap<String,String> queryParams = QueryParamUtil.convertToQueryParams(filter,pageable);
        return customerServiceWebClient
                .get()
                .uri(uriBuilder ->uriBuilder
                        .path("/api/v1/users/search")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<CustomerResponse>>(){});
    }


}
