package ma.waddad.bilingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.waddad.bilingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {

    @GetMapping("/api/customers/{id}")
    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable  Long id);


    @GetMapping("/api/customers")
    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "getDefaultCustomers")
    PagedModel<Customer> getAllCustomer();

    default Customer getDefaultCustomer(Long id, Exception e) {
        return Customer.builder()
                .id(id)
                .name("Default Name")
                .email("Default Email")
                .build();
    }

    default PagedModel<Customer> getDefaultCustomers(Exception exception) {

        return PagedModel.empty();

    }
}
