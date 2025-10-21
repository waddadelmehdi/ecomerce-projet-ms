package ma.waddad.bilingservice;

import ma.waddad.bilingservice.entities.Bill;
import ma.waddad.bilingservice.entities.ProductItem;
import ma.waddad.bilingservice.feign.CustomerRestClient;
import ma.waddad.bilingservice.feign.ProductRestClient;
import ma.waddad.bilingservice.model.Customer;
import ma.waddad.bilingservice.model.Product;
import ma.waddad.bilingservice.repositories.BillRepository;
import ma.waddad.bilingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@EnableFeignClients
@SpringBootApplication
public class BilingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilingServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(BillRepository  billRepository, ProductItemRepository  productItemRepository, CustomerRestClient customerRestClient,
                                        ProductRestClient  productRestClient) {

       return args -> {
           Collection<Customer> customers = customerRestClient.getAllCustomer().getContent();
           Collection<Product> products = productRestClient.getAllProduct().getContent();


           customers.forEach(customer -> {
               Bill bill = Bill.builder()
                       .billingDate(new Date())
                       .customerId(customer.getId())
                       .build();
               billRepository.save(bill);
               products.forEach(product -> {
                   ProductItem productItem = ProductItem.builder()
                           .bill(bill)
                           .productId(product.getId())
                           .quantity(new Random().nextInt(10))
                           .unitPrice(product.getPrice())
                           .build();
                   productItemRepository.save(productItem);
               });
           });
       };
    }

}
