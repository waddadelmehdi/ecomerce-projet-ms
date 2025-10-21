package ma.waddad.bilingservice.web;

import com.netflix.discovery.converters.Auto;
import ma.waddad.bilingservice.entities.Bill;
import ma.waddad.bilingservice.feign.CustomerRestClient;
import ma.waddad.bilingservice.feign.ProductRestClient;
import ma.waddad.bilingservice.repositories.BillRepository;
import ma.waddad.bilingservice.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {


    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }


    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
           productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;

    };
}
